package com.kurtoye.obsidianpos.payload.dto;

import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Customer;
import com.kurtoye.obsidianpos.models.OrderItem;
import com.kurtoye.obsidianpos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {


    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDTO branch;

    private UserDTO cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDTO> items;

}
