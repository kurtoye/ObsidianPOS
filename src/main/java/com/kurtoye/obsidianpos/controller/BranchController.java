package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.payload.dto.BranchDTO;
import com.kurtoye.obsidianpos.payload.response.ApiResponse;
import com.kurtoye.obsidianpos.repository.BranchRepository;
import com.kurtoye.obsidianpos.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {
        BranchDTO createdBranch = branchService.createBranch(branchDTO);
        return ResponseEntity.ok(createdBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {
        BranchDTO branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {

        List<BranchDTO> branches = branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(branches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id,
                                                  @RequestBody BranchDTO branchDTO) throws Exception {

        BranchDTO updatedBranch = branchService.updateBranch(id, branchDTO);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch successfully deleted");
        branchService.deleteBranch(id);
        return ResponseEntity.ok(apiResponse);
    }

}
