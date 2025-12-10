package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
    BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception;
    BranchDTO getBranchById(Long id) throws Exception;
    List<BranchDTO> getAllBranchesByStoreId(Long storeId);
    void deleteBranch(Long id) throws Exception;
}
