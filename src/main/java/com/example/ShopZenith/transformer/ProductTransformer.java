package com.example.ShopZenith.transformer;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Category;
import com.example.ShopZenith.entity.Product;

public class ProductTransformer {

    public static ProductDto prodcutToProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .byteImg(product.getImg())
                .categoryId(product.getCategory().getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
