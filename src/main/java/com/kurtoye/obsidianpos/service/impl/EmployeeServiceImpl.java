package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.domain.UserRole;
import com.kurtoye.obsidianpos.mapper.UserMapper;
import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import com.kurtoye.obsidianpos.repository.BranchRepository;
import com.kurtoye.obsidianpos.repository.StoreRepository;
import com.kurtoye.obsidianpos.repository.UserRepository;
import com.kurtoye.obsidianpos.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createStoreEmployee(UserDTO employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));
        Branch branch = null;

        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            if (employee.getBranchId() == null) {
                throw new Exception("Branch Id is required");
            }
            branch = branchRepository.findById(employee.getBranchId()).orElseThrow(() -> new Exception("Branch not found"));
        }

        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);
        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null) {
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }

        return UserMapper.toDTO(savedEmployee);
    }

    @Override
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));

        if (employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDTO(userRepository.save(user));
        }

        throw new Exception("Branch role not supported");
    }

    @Override
    public UserDTO updateEmployee(Long employeeId, UserDTO employee) throws Exception {
        User existingEmployee = userRepository.findById(employeeId).orElseThrow(() -> new Exception("Employee not found"));
        Branch branch = branchRepository.findById(employee.getBranchId()).orElseThrow(() -> new Exception("Branch not found"));

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setBranch(branch);
        return UserMapper.toDTO(existingEmployee);
    }

    @Override
    public List<UserDTO> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));
        return userRepository.findByStore(store).stream()
                .filter(user -> role == null || user.getRole() == role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findBranchEmployees(Long branchId, UserRole role) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));

        return userRepository.findByBranchId(branchId).stream()
                .filter(user -> role == null || user.getRole()== role)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(() -> new Exception("Employee not found"));
        userRepository.delete(employee);
    }
}
