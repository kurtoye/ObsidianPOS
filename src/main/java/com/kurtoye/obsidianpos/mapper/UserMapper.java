package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User savedUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getID());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setFullName(savedUser.getFullName());
        userDTO.setPhone(savedUser.getPhone());
        userDTO.setRole(savedUser.getRole());
        userDTO.setCreatedAt(savedUser.getCreatedAt());
        userDTO.setUpdatedAt(savedUser.getUpdatedAt());
        userDTO.setLastLoginAt(savedUser.getLastLoginAt());

        return userDTO;
    }
}
