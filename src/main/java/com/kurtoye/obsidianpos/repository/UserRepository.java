package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
