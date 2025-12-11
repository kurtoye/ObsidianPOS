package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.payload.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO updateInventory(Long inventoryId, InventoryDTO inventoryDTO) throws Exception;
    InventoryDTO getInventoryById(Long inventoryId) throws Exception;
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDTO> getAllInventoryByBranchId(Long branchId);
    void deleteInventory(Long inventoryId) throws Exception;
}
