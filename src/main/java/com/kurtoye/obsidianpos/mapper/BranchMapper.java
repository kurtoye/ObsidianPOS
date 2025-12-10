package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.payload.dto.BranchDTO;

import java.time.LocalDateTime;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .storeId(branch.getStore()!=null?branch.getStore().getId():null)
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();

    }

    public static Branch toEntity(BranchDTO branchDTO, Store store) {
        return Branch.builder()
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .phone(branchDTO.getPhone())
                .email(branchDTO.getEmail())
                .workingDays(branchDTO.getWorkingDays())
                .openTime(branchDTO.getOpenTime())
                .closeTime(branchDTO.getCloseTime())
                .store(store)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
