package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.domain.OrderStatus;
import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.mapper.OrderMapper;
import com.kurtoye.obsidianpos.models.*;
import com.kurtoye.obsidianpos.payload.dto.OrderDTO;
import com.kurtoye.obsidianpos.repository.OrderItemRepository;
import com.kurtoye.obsidianpos.repository.OrderRepository;
import com.kurtoye.obsidianpos.repository.ProductRepository;
import com.kurtoye.obsidianpos.service.OrderService;
import com.kurtoye.obsidianpos.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {
        User cashier = userService.getCurrentUser();

        Branch branch = cashier.getBranch();
        if (branch == null) {throw new Exception("Cashier's Branch is null");}

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDTO.getCustomer())
                .paymentType(orderDTO.getPaymentType())
                .build();

        List<OrderItem> items = orderDTO.getItems().stream().map(
                itemDto -> {
                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();

        double totalPrice = items.stream().mapToDouble(
                OrderItem:: getPrice
        ).sum();

        order.setTotalAmount(totalPrice);
        order.setItems(items);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }


    @Override
    public OrderDTO getOrderById(Long orderId) throws Exception {

        return orderRepository.findById(orderId)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new Exception("Order not found"));
    }

    @Override
    public List<OrderDTO> getOrdersByBranchId(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception {
        return orderRepository.findByBranchId(branchId).stream()
                .filter(order -> customerId == null
                        || (order.getCustomer()!= null && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId == null
                        || (order.getCashier() != null && order.getCashier().getId().equals(cashierId)))
                .filter(order -> paymentType == null
                        || (order.getPaymentType() == paymentType))
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCashierId(Long cashierId) throws Exception {
        return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getTodaysOrdersByBranch(Long branchId) throws Exception {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start, end).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getTopFiveRecentOrdersByBranchId(Long branchId) throws Exception {
        return orderRepository.findTopFiveByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

}
