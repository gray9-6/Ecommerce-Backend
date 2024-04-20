package com.example.ShopZenith.controller.admin;

import com.example.ShopZenith.dto.ProductDto;
import com.example.ShopZenith.entity.Product;
import com.example.ShopZenith.services.admin.product.AdminProductService;
import com.example.ShopZenith.services.admin.product.AdminProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;


    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws IOException {
        return new ResponseEntity<>(adminProductService.addProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(adminProductService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> getAllProductByName(@PathVariable(name = "name") String name){
        return new ResponseEntity<>(adminProductService.getAllProductByName(name),HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        boolean isDeleted = adminProductService.deleteProduct(productId);
        if(isDeleted){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
