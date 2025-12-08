package com.kurtoye.obsidianpos.service;


import com.kurtoye.obsidianpos.domain.StoreStatus;
import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;

import java.util.List;


public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO, User user);
    StoreDTO getStoreByID(Long storeID) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long storeID, StoreDTO storeDTO) throws Exception;
    void deleteStore(Long storeID) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;

    StoreDTO moderateStore(Long storeID, StoreStatus status) throws Exception;
}
