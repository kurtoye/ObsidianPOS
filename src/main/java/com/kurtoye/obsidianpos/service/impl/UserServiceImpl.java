package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.configuration.JwtProvider;
import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.repository.UserRepository;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String jwtToken) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwtToken);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found");
        }

        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found");
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found");
        }

        return user;
    }

    @Override
    public User getUserByID(Long id) throws UserException, Exception {

        return userRepository.findById(id).orElseThrow(
            () -> new Exception("User not found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
