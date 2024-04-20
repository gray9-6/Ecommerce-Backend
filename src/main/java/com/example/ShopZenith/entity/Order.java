package com.example.ShopZenith.entity;

import com.example.ShopZenith.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    Date date;

    Long amount;   // amount after discount

    String address;

    String payment;

    @Enumerated(value = EnumType.STRING)  // Specifies to store OrderStatus enum as string
    OrderStatus orderStatus;

    Long totalAmount;  // total amount

    Long discount;

    UUID trackingId;  // automatic generated id, which helps us to track order

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    List<CartItems> cartItems;
}
