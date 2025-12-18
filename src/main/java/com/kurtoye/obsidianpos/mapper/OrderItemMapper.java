package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.OrderItem;
import com.kurtoye.obsidianpos.payload.dto.OrderItemDTO;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        if (orderItem == null) return null;
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .product(ProductMapper.toDTO(orderItem.getProduct()))
                .build();
    }
}
