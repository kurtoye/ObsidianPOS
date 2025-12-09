package com.kurtoye.obsidianpos.mapper;

import com.kurtoye.obsidianpos.models.Category;
import com.kurtoye.obsidianpos.models.Product;
import com.kurtoye.obsidianpos.models.Store;
import com.kurtoye.obsidianpos.payload.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .categoryDTO(CategoryMapper.toDTO(product.getCategory())!=null?CategoryMapper.toDTO(product.getCategory()):null)
                .categoryId(product.getCategory().getId()!=null?product.getCategory().getId():null)
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
 //               .categoryID(product.get)
                

    }

    public static Product toEntity(ProductDTO productDTO, Store store, Category category) {
        return Product.builder()
                .name(productDTO.getName())
                .store(store)
                .category(category)
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice((productDTO.getSellingPrice()))
                .brand(productDTO.getBrand())
                .imageUrl(productDTO.getImageUrl())
                .build();
    }
}
