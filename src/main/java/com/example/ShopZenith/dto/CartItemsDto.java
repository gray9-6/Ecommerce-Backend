package com.example.ShopZenith.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemsDto {

    Long id;
    Long price;
    Long quantity;
    Long productId;
    Long orderId;
    String productName;
    byte[] returnedImg;
    Long userId;
}
