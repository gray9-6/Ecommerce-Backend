package com.example.ShopZenith.services.customer;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Product;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProducts();
    List<Product> searchProductByTitle(String name);
}
