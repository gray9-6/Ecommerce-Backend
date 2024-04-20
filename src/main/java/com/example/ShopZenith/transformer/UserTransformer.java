package com.example.ShopZenith.transformer;

import com.example.ShopZenith.dto.UserResponseDto;
import com.example.ShopZenith.entity.User;

public class UserTransformer {

    public static UserResponseDto UserToUserResponseDto(User user){
        return  UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .userRole(user.getUserRole())
                .build();
    }
}
