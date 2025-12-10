package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.domain.StoreStatus;
import com.kurtoye.obsidianpos.mapper.StoreMapper;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.StoreDTO;
import com.kurtoye.obsidianpos.payload.response.ApiResponse;
import com.kurtoye.obsidianpos.service.StoreService;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody  StoreDTO storeDTO,
                                                @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDTO, user));
    }


    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getAllStores(@RequestHeader("Authorization")String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(@RequestHeader("Authorization")String jwt) throws Exception {

        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(@RequestHeader("Authorization")String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(@PathVariable Long id,
                                                  @RequestBody StoreDTO storeDTO,
                                                  @RequestParam StoreStatus status) throws Exception {

        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreByID(@PathVariable Long id,
                                                 @RequestHeader("Authorization")String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreByID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
