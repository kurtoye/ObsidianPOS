package com.kurtoye.obsidianpos.payload.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kurtoye.obsidianpos.domain.UserRole;
import lombok.Data;

import java.time.LocalDateTime;


@JsonPropertyOrder({
        "ID",
        "email",
        "fullName",
        "password",
        "phone",
        "role",
        "createdAt",
        "updatedAt",
        "lastLoginAt",

})
@Data
public class UserDTO {


    private Long id;
    private String password;
    private UserRole role;

    private String fullName;
    private String email;
    private String phone;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

}
