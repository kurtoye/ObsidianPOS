package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;

public class StoreMapper {

    public static StoreDTO toDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getId());
        storeDTO.setBrand(store.getBrand());
        storeDTO.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));

        storeDTO.setStoreType(store.getStoreType());
        storeDTO.setStatus(store.getStatus());
        storeDTO.setDescription(store.getDescription());
        storeDTO.setContact(store.getContact());

        storeDTO.setCreatedAt(store.getCreatedAt());
        storeDTO.setUpdatedAt(store.getUpdatedAt());

        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO, User storeAdmin) {
        Store store = new Store();
        store.setId(storeDTO.getId());
        store.setBrand(storeDTO.getBrand());
        store.setStoreAdmin(storeAdmin);

        store.setStoreType(storeDTO.getStoreType());
        store.setStatus(storeDTO.getStatus());
        store.setDescription(storeDTO.getDescription());
        store.setContact(storeDTO.getContact());

        store.setCreatedAt(storeDTO.getCreatedAt());
        store.setUpdatedAt(storeDTO.getUpdatedAt());

        return store;
    }
}
