package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.User;

import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String jwtToken) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();
}
