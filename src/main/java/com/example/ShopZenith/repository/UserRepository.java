package com.example.ShopZenith.repository;

import com.example.ShopZenith.entity.User;
import com.example.ShopZenith.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);

//    Optional<User> findByRole(UserRole userRole);  // incorrect , becoz we have userRole, not role
    Optional<User> findByUserRole(UserRole userRole); // Corrected method name
}
