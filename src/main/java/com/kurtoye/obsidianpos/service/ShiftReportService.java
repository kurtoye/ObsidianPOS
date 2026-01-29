package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.models.ShiftReport;
import com.kurtoye.obsidianpos.payload.dto.ShiftReportDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift() throws Exception;
    ShiftReportDTO endShift() throws Exception;
    ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception;
    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) throws Exception;
    ShiftReportDTO getShiftReportsByCashierIdAndDate(Long cashierId, LocalDate date) throws Exception;
    List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) throws Exception;
    List<ShiftReportDTO> getAllShiftReports() throws Exception;


    ShiftReportDTO getCurrentShiftProgress() throws Exception;
}
