package com.example.ShopZenith.entity;

import com.example.ShopZenith.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Enumerated(value = EnumType.STRING)
    UserRole userRole;

    @Lob  // to store the large image
    @Column(columnDefinition = "longblob")
    byte[] img;  // to store the user images
}
