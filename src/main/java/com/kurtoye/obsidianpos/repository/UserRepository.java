package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Branch;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
