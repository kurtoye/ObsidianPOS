package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    List<Inventory> findByBranchId(Long branchId);
    Inventory findByProductIdAndBranchId(Long productId, Long branchId);
}
