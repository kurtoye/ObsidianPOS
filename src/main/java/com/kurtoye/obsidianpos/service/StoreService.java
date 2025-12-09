package com.kurtoye.obsidianpos.service;


import com.kurtoye.obsidianpos.domain.StoreStatus;
import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;

import java.util.List;


public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO, User user);
    StoreDTO getStoreByID(Long storeId) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long storeId, StoreDTO storeDTO) throws Exception;
    void deleteStore(Long storeId) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;

    StoreDTO moderateStore(Long storeId, StoreStatus status) throws Exception;
}
