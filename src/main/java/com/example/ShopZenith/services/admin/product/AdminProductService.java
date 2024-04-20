package com.example.ShopZenith.services.admin.product;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Product;
import io.jsonwebtoken.io.IOException;

import java.util.List;

public interface AdminProductService {
    ProductDto addProduct(ProductDto productDto) throws IOException, java.io.IOException;
    List<ProductDto> getAllProducts();
    List<Product> getAllProductByName(String name);
    boolean deleteProduct(Long id);

}
