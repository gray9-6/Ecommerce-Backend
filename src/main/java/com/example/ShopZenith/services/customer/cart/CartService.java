package com.example.ShopZenith.services.customer.cart;

import com.example.ShopZenith.dto.AddProductInCartDto;
import com.example.ShopZenith.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);
}
