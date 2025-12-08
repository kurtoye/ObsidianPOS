package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import com.kurtoye.obsidianpos.payload.response.AuthResponse;
import jakarta.validation.constraints.NotNull;

public interface AuthService {

    AuthResponse register(UserDTO userDTO) throws UserException;
    AuthResponse login(UserDTO userDTO) throws UserException;
}
