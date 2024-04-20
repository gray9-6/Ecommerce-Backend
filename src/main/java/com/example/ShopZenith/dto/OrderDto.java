package com.example.ShopZenith.dto;


import com.example.ShopZenith.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDto {


    Long id;

    String description;

    Date date;

    Long amount;   // amount after discount

    String address;

    String payment;

    OrderStatus orderStatus;

    Long totalAmount;  // total amount

    Long discount;

    UUID trackingId;  // automatic generated id, which helps us to track order


    String userName;

    List<CartItemsDto> cartItems;
}
