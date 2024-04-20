package com.example.ShopZenith.controller;

import com.example.ShopZenith.dto.AuthenticateRequest;
import com.example.ShopZenith.dto.SignupRequestDto;
import com.example.ShopZenith.dto.UserResponseDto;
import com.example.ShopZenith.entity.User;
import com.example.ShopZenith.repository.UserRepository;
import com.example.ShopZenith.services.auth.AuthService;
import com.example.ShopZenith.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest, HttpServletResponse response) throws JSONException, IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),authenticateRequest.getPassword()));
        }catch (BadCredentialsException badCredentialsException){
            throw new BadCredentialsException("Incorrect username or password");
        }

        // if the user is authenticated successfully
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if(optionalUser.isPresent()){
            response.getWriter().write(
                    new JSONObject()
                            .put("userId",optionalUser.get().getId())
                            .put("role",optionalUser.get().getUserRole())
                            .toString()
            );

            // this two lines are to expose the headers to frontend
            response.addHeader("Access-Control-Expose-Headers","Authorization");
            response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Oigin, " +
                    "X-Requested-With, Content-Type, Accept, X-Custom-header");


            response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequestDto signupRequestDto){
        // check if the user already exists
        if(authService.hasUserWithEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("user with this email id already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        // if not exists then create the user
        UserResponseDto createdUser = authService.createUser(signupRequestDto);

        // return the response
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);

    }
}
