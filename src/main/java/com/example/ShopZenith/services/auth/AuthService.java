package com.example.ShopZenith.services.auth;

import com.example.ShopZenith.dto.SignupRequestDto;
import com.example.ShopZenith.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto createUser (SignupRequestDto signupRequestDto);
    Boolean hasUserWithEmail(String emailId);
}
