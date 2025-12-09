package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.payload.dto.CategoryDTO;
import com.kurtoye.obsidianpos.payload.response.ApiResponse;
import com.kurtoye.obsidianpos.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) throws Exception {
        return ResponseEntity.ok(
                categoryService.createCategory(dto)
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategory(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(
                categoryService.getCategoriesByStore(storeId)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable Long id) throws Exception {

        categoryService.updateCategory(id, categoryDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
