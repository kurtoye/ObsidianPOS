package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.models.Refund;
import com.kurtoye.obsidianpos.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(RefundDTO refundDTO) throws Exception;
    RefundDTO getRefundById(Long refundId) throws Exception;
    List<RefundDTO> getRefundByCashierId(Long cashierId) throws Exception;
    List<RefundDTO> getRefundByShiftReportId(Long shiftReportId) throws Exception;
    List<RefundDTO> getRefundByBranchId(Long branchId) throws Exception;
    List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    List<RefundDTO> getAllRefunds() throws Exception;
    void deleteRefundById(Long refundId) throws Exception;
}
