package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User savedUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setFullName(savedUser.getFullName());
        userDTO.setPhone(savedUser.getPhone());
        userDTO.setRole(savedUser.getRole());
        userDTO.setCreatedAt(savedUser.getCreatedAt());
        userDTO.setUpdatedAt(savedUser.getUpdatedAt());
        userDTO.setLastLoginAt(savedUser.getLastLoginAt());
        userDTO.setBranchId(savedUser.getBranch()!=null?savedUser.getBranch().getId():null);
        userDTO.setStoreId(savedUser.getStore()!=null?savedUser.getStore().getId():null);
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setLastLoginAt(userDTO.getLastLoginAt());

        return user;
    }
}
