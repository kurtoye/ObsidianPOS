package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminId(Long adminId);
}
