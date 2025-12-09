package com.kurtoye.obsidianpos.repository;

import com.kurtoye.obsidianpos.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByStoreId(long storeId);

}
