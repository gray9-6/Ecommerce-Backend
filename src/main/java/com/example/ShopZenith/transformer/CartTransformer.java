package com.example.ShopZenith.transformer;

import com.example.ShopZenith.dto.CartItemsDto;
import com.example.ShopZenith.entity.CartItems;

public class CartTransformer {

    public static CartItemsDto cartToCartItemDto(CartItems cartItems){
        return CartItemsDto.builder()
                .id(cartItems.getId())
                .price(cartItems.getPrice())
                .quantity(cartItems.getQuantity())
                .productId(cartItems.getProduct().getId())
                .orderId(cartItems.getOrder().getId())
                .productName(cartItems.getProduct().getName())
                .userId(cartItems.getUser().getId())
                .returnedImg(cartItems.getProduct().getImg())
                .build();
    }
}
