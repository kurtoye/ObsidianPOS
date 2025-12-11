package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.domain.UserRole;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import com.kurtoye.obsidianpos.payload.response.ApiResponse;
import com.kurtoye.obsidianpos.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDTO> createStoreEmployee(@RequestBody UserDTO userDTO,
                                                       @PathVariable Long storeId) throws Exception {
        UserDTO employee = employeeService.createStoreEmployee(userDTO, storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDTO> createBranchEmployee(@RequestBody UserDTO userDTO,
                                                        @PathVariable Long branchId) throws Exception {
        UserDTO employee = employeeService.createBranchEmployee(userDTO, branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@RequestBody UserDTO userDTO,
                                               @PathVariable Long id) throws Exception {

        User employee = employeeService.updateEmployee(id, userDTO);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDTO>> getStoreEmployees(@PathVariable Long id,
                                                        @RequestParam(required = false) UserRole userRole) throws Exception {

        List<UserDTO> employee = employeeService.findStoreEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDTO>> getBranchEmployees(@PathVariable Long id,
                                                        @RequestParam(required = false) UserRole userRole) throws Exception {

        List<UserDTO> employee = employeeService.findBranchEmployees(id,userRole);
        return ResponseEntity.ok(employee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Employee deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
