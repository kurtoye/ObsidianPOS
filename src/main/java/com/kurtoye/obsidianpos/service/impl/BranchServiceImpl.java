package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.mapper.BranchMapper;
import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.BranchDTO;
import com.kurtoye.obsidianpos.repository.BranchRepository;
import com.kurtoye.obsidianpos.repository.StoreRepository;
import com.kurtoye.obsidianpos.service.BranchService;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDTO, store);
        Branch savedBranch = branchRepository.save(branch);

        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found")
        );

        branch.setName(branchDTO.getName());
        branch.setWorkingDays(branchDTO.getWorkingDays());
        branch.setEmail(branchDTO.getEmail());
        branch.setAddress(branchDTO.getAddress());
        branch.setPhone(branchDTO.getPhone());
        branch.setOpenTime(branchDTO.getOpenTime());
        branch.setCloseTime(branchDTO.getCloseTime());
        branch.setUpdatedAt(LocalDateTime.now());
        Branch updatedBranch = branchRepository.save(branch);

        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found")
        );
        return BranchMapper.toDTO(branch);
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new Exception("Branch not found")
        );
        branchRepository.delete(branch);
    }
}
