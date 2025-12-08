package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.domain.StoreStatus;
import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.mapper.StoreMapper;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.StoreContact;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;
import com.kurtoye.obsidianpos.repository.StoreRepository;
import com.kurtoye.obsidianpos.service.StoreService;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;


    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {
        Store store = StoreMapper.toEntity(storeDTO, user);
        return StoreMapper.toStoreDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreByID(Long storeID) throws Exception {
        Store store = storeRepository.findById(storeID).orElseThrow(
                () -> new Exception("Store not found")
        );
        return StoreMapper.toStoreDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(StoreMapper::toStoreDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminID(admin.getID());
    }

    @Override
    public StoreDTO updateStore(Long storeID, StoreDTO storeDTO) throws Exception {
        User currentUser = userService.getCurrentUser();

        Store existingStore = storeRepository.findByStoreAdminID(currentUser.getID());
        if  (existingStore == null) {
            throw new Exception("Store not found");
        }

        existingStore.setBrand(storeDTO.getBrand());
        existingStore.setDescription(storeDTO.getDescription());

        if (storeDTO.getStoreType() != null) {
            existingStore.setStoreType(storeDTO.getStoreType());
        }

        if (storeDTO.getContact() != null) {
            StoreContact contact = StoreContact.builder()
                    .address(storeDTO.getContact().getAddress())
                    .phone(storeDTO.getContact().getPhone())
                    .email(storeDTO.getContact().getEmail())
                    .build();
            existingStore.setContact(contact);
        }

        Store updatedStore = storeRepository.save(existingStore);

        return StoreMapper.toStoreDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long storeID) throws UserException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new UserException("User doesn't have permission to access this store");
        }
        return StoreMapper.toStoreDTO(currentUser.getStore());
    }

    @Override
    public StoreDTO moderateStore(Long storeID, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(storeID).orElseThrow(
                () -> new Exception("Store not found")
        );

        store.setStatus(status);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toStoreDTO(updatedStore);
    }
}
