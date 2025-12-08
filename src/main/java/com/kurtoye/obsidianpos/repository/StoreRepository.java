package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminID(Long adminID);
}
