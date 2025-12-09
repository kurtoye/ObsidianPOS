package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Category;
import com.kurtoye.obsidianpos.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();
    }
}
