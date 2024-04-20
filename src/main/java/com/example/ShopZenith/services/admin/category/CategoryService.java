package com.example.ShopZenith.services.admin.category;

import com.example.ShopZenith.dto.CategoryDto;
import com.example.ShopZenith.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
}
