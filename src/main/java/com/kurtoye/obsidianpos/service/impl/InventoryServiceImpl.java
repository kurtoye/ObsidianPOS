package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.mapper.InventoryMapper;
import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Inventory;
import com.kurtoye.obsidianpos.models.Product;
import com.kurtoye.obsidianpos.payload.dto.InventoryDTO;
import com.kurtoye.obsidianpos.repository.BranchRepository;
import com.kurtoye.obsidianpos.repository.InventoryRepository;
import com.kurtoye.obsidianpos.repository.ProductRepository;
import com.kurtoye.obsidianpos.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        Branch branch = branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
                () -> new Exception("Branch not found")
        );

        Product product = productRepository.findById(inventoryDTO.getProductId()).orElseThrow(
                () -> new Exception("Product not found")
        );

        Inventory inventory = InventoryMapper.toEntity(inventoryDTO, branch, product);
        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long inventoryId, InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                () -> new Exception("Inventory not found")
        );

        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public InventoryDTO getInventoryById(Long inventoryId) throws Exception {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                () -> new Exception("Inventory not found")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteInventory(Long inventoryId) throws Exception {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(
                () -> new Exception("Inventory not found")
        );
        inventoryRepository.delete(inventory);
    }
}
