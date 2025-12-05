package com.kurtoye.obsidianpos.payload.response;

import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDTO user;


}
