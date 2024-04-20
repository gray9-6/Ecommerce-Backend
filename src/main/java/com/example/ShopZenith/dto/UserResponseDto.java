package com.example.ShopZenith.dto;

import com.example.ShopZenith.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserResponseDto {
    Long id;
    String email;
    String name;
    UserRole userRole;
}
