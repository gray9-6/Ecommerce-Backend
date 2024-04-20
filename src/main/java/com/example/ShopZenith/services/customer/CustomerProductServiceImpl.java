package com.example.ShopZenith.services.customer;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Product;
import com.example.ShopZenith.repository.ProductRepository;
import com.example.ShopZenith.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product :products) {
            productDtoList.add(ProductTransformer.prodcutToProductDto(product));
        }

        return productDtoList;
    }

    public List<Product> searchProductByTitle(String name){
        List<Product> productListByName = productRepository.findAllByNameContaining(name);
        return productListByName;
    }
}
