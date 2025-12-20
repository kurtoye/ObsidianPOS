package com.kurtoye.obsidianpos.payload.dto;

import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.models.ShiftReport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDTO {

    private Long id;

    private OrderDTO order;
    private Long orderId;

    private String reason;

    private Double amount;

    private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDTO cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;

    private PaymentType paymentType;
    private LocalDateTime createdAt;


}
