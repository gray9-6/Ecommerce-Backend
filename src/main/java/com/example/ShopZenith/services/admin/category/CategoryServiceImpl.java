package com.example.ShopZenith.services.admin.category;

import com.example.ShopZenith.dto.CategoryDto;
import com.example.ShopZenith.entity.Category;
import com.example.ShopZenith.repository.CategoryRepository;
import com.example.ShopZenith.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDto categoryDto){
        // create a category from the dto
        Category newCategory = Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();

        // save the category and return it
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
