package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
