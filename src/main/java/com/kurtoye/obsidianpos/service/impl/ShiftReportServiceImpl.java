package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.mapper.ShiftReportMapper;
import com.kurtoye.obsidianpos.models.*;
import com.kurtoye.obsidianpos.payload.dto.ShiftReportDTO;
import com.kurtoye.obsidianpos.repository.OrderRepository;
import com.kurtoye.obsidianpos.repository.RefundRepository;
import com.kurtoye.obsidianpos.repository.ShiftReportRepository;
import com.kurtoye.obsidianpos.repository.UserRepository;
import com.kurtoye.obsidianpos.service.ShiftReportService;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public ShiftReportDTO startShift() throws Exception {

        User cashier = userService.getCurrentUser();

        LocalDateTime shiftStart = LocalDateTime.now();
        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(cashier, startOfDay, endOfDay);

        if (existing.isPresent()) throw new Exception("Shift already started today");

        Branch branch = cashier.getBranch();

        ShiftReport newShiftReport = ShiftReport.builder()
                .cashier(cashier)
                .branch(branch)
                .shiftStart(shiftStart)
                .build();

        ShiftReport savedShiftReport = shiftReportRepository.save(newShiftReport);

        return ShiftReportMapper.toDTO(savedShiftReport);
    }

    @Override
    public ShiftReportDTO endShift() throws Exception {
        User cashier = userService.getCurrentUser();
        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(cashier)
                .orElseThrow(() -> new Exception("Shift not found"));

        LocalDateTime shiftEnd = LocalDateTime.now();
        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(cashier.getId(),  shiftReport.getShiftStart(), shiftReport.getShiftEnd());
        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(cashier, shiftReport.getShiftStart(), shiftReport.getShiftEnd());

        double totalRefunds = refunds.stream().mapToDouble( refund -> refund.getAmount() != null ? refund.getAmount(): 0.0).sum();
        double totalSales = orders.stream().mapToDouble(Order:: getTotalAmount).sum();
        int totalOrders = orders.size();
        double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setNetSales(netSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedShiftReport = shiftReportRepository.save(shiftReport);

        return ShiftReportMapper.toDTO(savedShiftReport);
    }


    @Override
    public ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception {
        return ShiftReportMapper.toDTO(shiftReportRepository.findById(shiftReportId).orElseThrow(() -> new Exception("Shift Report not found")));
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) throws Exception {
        List<ShiftReport> shiftReports = shiftReportRepository.findByCashierId(cashierId);
        return shiftReports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getShiftReportsByCashierIdAndDate(Long cashierId, LocalDate date) throws Exception {
        User cashier = userRepository.findById(cashierId)
                .orElseThrow(() -> new Exception("Cashier not found"));

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23,59,59);

        ShiftReport shiftReport = shiftReportRepository.findByCashierAndShiftStartBetween(cashier, start, end)
                .orElseThrow(() -> new Exception("Shift Report not found"));

        return ShiftReportMapper.toDTO(shiftReport);
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) throws Exception {
        List<ShiftReport> shiftReports = shiftReportRepository.findByBranchId(branchId);
        return shiftReports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() throws Exception {
        List<ShiftReport> shiftReports = shiftReportRepository.findAll();
        return shiftReports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress() throws Exception {
        User cashier = userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(cashier)
                .orElseThrow(() -> new Exception("No active shift found"));

        LocalDateTime now = LocalDateTime.now();

        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(cashier, shiftReport.getShiftStart(), now);
        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(cashier.getId(),  shiftReport.getShiftStart(), now);

        double totalRefunds = refunds.stream().mapToDouble( refund -> refund.getAmount() != null ? refund.getAmount(): 0.0).sum();
        double totalSales = orders.stream().mapToDouble(Order:: getTotalAmount).sum();
        int totalOrders = orders.size();
        double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setNetSales(netSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftReportRepository.save(shiftReport);

        return ShiftReportMapper.toDTO(shiftReport);
    }

    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {
        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getPaymentType() != null ? order.getPaymentType(): PaymentType.CARD));

        List<PaymentSummary> paymentSummaries = new ArrayList<>();
        for (Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()) {
            double amount = entry.getValue().stream().mapToDouble(Order:: getTotalAmount).sum();
            int transactions = entry.getValue().size();
            double percentage = (amount/totalSales)*100;

            PaymentSummary paymentSummary = new PaymentSummary();
            paymentSummary.setPaymentType(entry.getKey());
            paymentSummary.setTotalAmount(amount);
            paymentSummary.setTransactionCount(transactions);
            paymentSummary.setPercentage(percentage);
            paymentSummaries.add(paymentSummary);
        }
        return paymentSummaries;
    }

    // --- Helper Methods
    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product, Integer> productSalesMap = new HashMap<>();

        for (Order order : orders) {
            for (OrderItem orderItem : order.getItems()) {
                Product product = orderItem.getProduct();
                productSalesMap.put(product, productSalesMap.getOrDefault(product, 0) + orderItem.getQuantity());
            }
        }
        return productSalesMap.entrySet().stream()
                .sorted((a, b)-> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

}
