package com.kurtoye.obsidianpos.payload.dto;

import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long id;

    private BranchDTO branch;
    private Long branchId;

    private ProductDTO product;
    private Long productId;

    private Integer quantity;

    private LocalDateTime lastUpdated;

}
