package com.kurtoye.obsidianpos.service.impl;

import com.kurtoye.obsidianpos.mapper.ProductMapper;
import com.kurtoye.obsidianpos.models.Category;
import com.kurtoye.obsidianpos.models.Product;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.models.User;
import com.kurtoye.obsidianpos.payload.dto.ProductDTO;
import com.kurtoye.obsidianpos.repository.CategoryRepository;
import com.kurtoye.obsidianpos.repository.ProductRepository;
import com.kurtoye.obsidianpos.repository.StoreRepository;
import com.kurtoye.obsidianpos.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
        Store store = storeRepository.findById(
                productDTO.getStoreId()
        ).orElseThrow(()-> new Exception("Store not found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new Exception("Category not found"));

        Product product = ProductMapper.toEntity(productDTO, store, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImageUrl(product.getImageUrl());
        product.setBrand(product.getBrand());
        product.setMrp(product.getMrp());
        product.setSellingPrice(product.getSellingPrice());
        product.setUpdatedAt(LocalDateTime.now());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product not found")
        );

        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
         List<Product> products =productRepository.findByStoreId(storeId);
         return products.stream()
                 .map(ProductMapper::toDTO)
                 .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> products =productRepository.searchByKeyword(storeId, keyword);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
