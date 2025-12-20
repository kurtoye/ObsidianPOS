package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Refund;
import com.kurtoye.obsidianpos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByCashierIdAndCreatedAtBetween(Long cashierId, LocalDateTime startDate, LocalDateTime endDate);
    List<Refund> findByCashierId(Long cashierId);
    List<Refund> findByShiftReportId(Long shiftReportId);
    List<Refund> findByBranchId(Long branchId);

}
