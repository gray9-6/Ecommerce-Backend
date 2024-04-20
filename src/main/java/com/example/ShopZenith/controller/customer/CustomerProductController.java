package com.example.ShopZenith.controller.customer;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Product;
import com.example.ShopZenith.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(customerProductService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> getAllProductByName(@PathVariable(name = "name") String name){
        return new ResponseEntity<>(customerProductService.searchProductByTitle(name),HttpStatus.OK);
    }
}
