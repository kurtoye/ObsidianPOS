package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Order;
import com.kurtoye.obsidianpos.models.Product;
import com.kurtoye.obsidianpos.models.Refund;
import com.kurtoye.obsidianpos.models.ShiftReport;
import com.kurtoye.obsidianpos.payload.dto.OrderDTO;
import com.kurtoye.obsidianpos.payload.dto.ProductDTO;
import com.kurtoye.obsidianpos.payload.dto.ShiftReportDTO;
import com.kurtoye.obsidianpos.payload.dto.RefundDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport shiftReport) {
        return ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSales(shiftReport.getTotalSales())
                .totalRefunds(shiftReport.getTotalRefunds())
                .totalOrders(shiftReport.getTotalOrders())
                .netSales(shiftReport.getNetSales())
                .cashier(UserMapper.toDTO(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .topSellingProducts(mapProducts(shiftReport.getTopSellingProducts()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .paymentSummaries(shiftReport.getPaymentSummaries())
                .build();
    }


    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if (refunds == null || refunds.isEmpty()) return null;
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if (topSellingProducts == null || topSellingProducts.isEmpty()) return null;
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
        if (recentOrders == null || recentOrders.isEmpty()) return null;
        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }


}
