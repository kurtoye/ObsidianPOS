package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Order;
import com.kurtoye.obsidianpos.payload.dto.OrderDTO;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
