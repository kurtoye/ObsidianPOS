package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
