package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.domain.UserRole;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;

import java.util.List;

public interface EmployeeService {

    UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception;
    UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception;
    User updateEmployee(Long employeeId, UserDTO employee) throws Exception;
    List<UserDTO> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<UserDTO> findBranchEmployees(Long branchId, UserRole role) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
}
