package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.payload.dto.ShiftReportDTO;
import com.kurtoye.obsidianpos.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shift-reports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDTO> startShift() throws Exception {
        return ResponseEntity.ok(shiftReportService.startShift());
    }

    @PatchMapping("/end")
    public ResponseEntity<ShiftReportDTO> endShift(@RequestParam Long shiftReportId) throws Exception {
        return ResponseEntity.ok(shiftReportService.endShift());
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDTO> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(shiftReportService.getCurrentShiftProgress());
    }

    @GetMapping("/{shiftReportId}")
    public ResponseEntity<ShiftReportDTO> getShiftReportById(@PathVariable Long shiftReportId) throws Exception {
        return ResponseEntity.ok(shiftReportService.getShiftReportById(shiftReportId));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ShiftReportDTO>> getShiftReportByCashier(@PathVariable long cashierId) throws Exception {
        return ResponseEntity.ok(shiftReportService.getShiftReportsByCashierId(cashierId));
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    public ResponseEntity<ShiftReportDTO> getShiftReportByDate(@PathVariable long cashierId, @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) throws Exception {
        return ResponseEntity.ok(shiftReportService.getShiftReportsByCashierIdAndDate(cashierId, date));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDTO>> getShiftReportByBranchId(@PathVariable long branchId) throws Exception {
        return ResponseEntity.ok(shiftReportService.getShiftReportsByBranchId(branchId));
    }


}
