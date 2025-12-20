package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Refund;
import com.kurtoye.obsidianpos.payload.dto.RefundDTO;

public class RefundMapper {

    public static RefundDTO toDTO(Refund refund) {
        return RefundDTO.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch() != null ? refund.getBranch().getId(): null)
                .shiftReportId(refund.getShiftReport() != null ? refund.getShiftReport().getId(): null)
                .createdAt(refund.getCreatedAt())
                .build();
    }
}
