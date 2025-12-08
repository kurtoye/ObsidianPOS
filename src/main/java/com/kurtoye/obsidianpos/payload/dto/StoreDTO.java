package com.kurtoye.obsidianpos.payload.dto;

import com.kurtoye.obsidianpos.domain.StoreStatus;
import com.kurtoye.obsidianpos.models.StoreContact;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDTO
{

    private Long id;
    private String brand;
    private UserDTO storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;
}
