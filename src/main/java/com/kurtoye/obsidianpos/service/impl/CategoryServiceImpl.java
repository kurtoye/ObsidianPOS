package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.domain.UserRole;
import com.kurtoye.obsidianpos.mapper.CategoryMapper;
import com.kurtoye.obsidianpos.models.Category;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.CategoryDTO;
import com.kurtoye.obsidianpos.repository.CategoryRepository;
import com.kurtoye.obsidianpos.repository.StoreRepository;
import com.kurtoye.obsidianpos.service.CategoryService;
import com.kurtoye.obsidianpos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) throws Exception {
        User user = userService.getCurrentUser();

        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        checkAuthority(user, category.getStore());


        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found")
        );

        User user = userService.getCurrentUser();

        checkAuthority(user, category.getStore());
        category.setName(dto.getName());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found")
        );
        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if (!(isAdmin && isSameStore) && !isManager){
            throw new Exception("You don't have permission to manage this category");
        }
    }
}
