package com.example.ShopZenith.services.admin.product;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Category;
import com.example.ShopZenith.entity.Product;
import com.example.ShopZenith.repository.CategoryRepository;
import com.example.ShopZenith.repository.ProductRepository;
import com.example.ShopZenith.transformer.ProductTransformer;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException, java.io.IOException {

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        Product createdProduct = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
//                .img(productDto.getImg().getBytes())   // uncomment this when you are trying to add the image
                .category(category)

                .build();

        // save the product
        Product savedProduct = productRepository.save(createdProduct);

        // convert the saved product and return it
        return ProductTransformer.prodcutToProductDto(savedProduct);


    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product :products) {
            productDtoList.add(ProductTransformer.prodcutToProductDto(product));
        }

        return productDtoList;
    }

    public List<Product> getAllProductByName(String name){
        List<Product> productListByName = productRepository.findAllByNameContaining(name);
        return productListByName;
    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
