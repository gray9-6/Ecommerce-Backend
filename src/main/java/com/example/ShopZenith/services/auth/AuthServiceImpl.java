package com.example.ShopZenith.services.auth;

import com.example.ShopZenith.dto.SignupRequestDto;
import com.example.ShopZenith.dto.UserResponseDto;
import com.example.ShopZenith.entity.Order;
import com.example.ShopZenith.entity.User;
import com.example.ShopZenith.enums.OrderStatus;
import com.example.ShopZenith.enums.UserRole;
import com.example.ShopZenith.repository.OrderRepository;
import com.example.ShopZenith.repository.UserRepository;
import com.example.ShopZenith.transformer.UserTransformer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;


    // create the customer account
    public UserResponseDto createUser (SignupRequestDto signupRequestDto){

        // create the user from the request dto
        User user = User.builder()
                .email(signupRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(signupRequestDto.getPassword()))
                .name(signupRequestDto.getName())
                .userRole(UserRole.CUSTOMER)       // this api is only for the customer
                .build();

        // save the user
        User savedUser = userRepository.save(user);

        // create a cart for the user
        Order order = Order.builder()
                .amount(0L)
                .totalAmount(0L)
                .discount(0L)
                .user(savedUser)
                .orderStatus(OrderStatus.PENDING)
                .build();
        // save the order
        orderRepository.save(order);

        // prepare the user response from the saved user
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setId(savedUser.getId());
//        return userResponseDto;
        return UserTransformer.UserToUserResponseDto(savedUser);
    }

    public Boolean hasUserWithEmail(String emailId){
        return userRepository.findFirstByEmail(emailId).isPresent();
    }



    @PostConstruct   // so it will automatically get called , after the constructor
    public void createAdminAccount(){  // create the Admin Account

        //create the admin user
        Optional<User> optionalAdminAccount = userRepository.findByUserRole(UserRole.ADMIN);

        // if the adminAccount is null then only create the admin, otherwise admin already exists
        if(optionalAdminAccount.isEmpty()){
            // create the user
            User adminCreated = User.builder()
                    .name("admin")
                    .email("admin@gmail.com")
                    .userRole(UserRole.ADMIN)
                    .password(new BCryptPasswordEncoder().encode("admin@123"))
                    .build();
            // save the created admin
            userRepository.save(adminCreated);

            log.info("Admin account created Successfully");
        }

        log.info("Admin Account Already Exists !!");
    }
}

