package com.example.ShopZenith.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The CartItems class represents items in a shopping cart.
 * It is an entity class mapped to a database table using JPA annotations.
 *
 * This class contains fields to store information about each cart item, such as:
 * - id: The unique identifier of the cart item.
 * - price: The price of the item.
 * - quantity: The quantity of the item in the cart.
 * - product: The product associated with the cart item. It is a many-to-one relationship
 *   with the Product entity, indicating that each cart item belongs to a single product.
 * - user: The user associated with the cart item. It is a many-to-one relationship
 *   with the User entity, indicating that each cart item belongs to a single user.
 * - order: The order associated with the cart item. It is a one-to-one relationship
 *   with the Order entity, indicating that each cart item can be associated with at most one order.
 *
 * The FetchType.LAZY annotation is used for the product and user relationships,
 * indicating that these associations should be loaded lazily (i.e., when accessed).
 *
 * The OnDelete annotation with action = OnDeleteAction.CASCADE is applied to the product and user
 * relationships, specifying that if a related product or user is deleted, the corresponding
 * cart item should also be deleted from the database to maintain referential integrity.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long price;

    Long quantity;

    /**
     * Represents the product associated with the cart item.
     * It is a many-to-one relationship with the Product entity,
     * indicating that each cart item belongs to a single product.
     * The product_id column in the database table is used as the foreign key
     * to establish the relationship between cart items and products.
     * The FetchType.LAZY annotation specifies that the product association should be loaded lazily (on-demand).
     * The optional = false attribute indicates that the product association is required (cannot be null).
     * The OnDelete annotation with action = OnDeleteAction.CASCADE specifies that if the associated product is deleted,
     * the corresponding cart item should also be deleted to maintain referential integrity.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    /**
     * Represents the user associated with the cart item.
     * It is a many-to-one relationship with the User entity,
     * indicating that each cart item belongs to a single user.
     * The user_id column in the database table is used as the foreign key
     * to establish the relationship between cart items and users.
     * The FetchType.LAZY annotation specifies that the user association should be loaded lazily (on-demand).
     * The optional = false attribute indicates that the user association is required (cannot be null).
     * The OnDelete annotation with action = OnDeleteAction.CASCADE specifies that if the associated user is deleted,
     * the corresponding cart item should also be deleted to maintain referential integrity.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    /**
     * Represents the order associated with the cart item.
     * It is a one-to-one relationship with the Order entity,
     * indicating that each cart item can be associated with at most one order.
     * The order_id column in the database table is used as the foreign key
     * to establish the relationship between cart items and orders.
     * The FetchType.LAZY annotation specifies that the order association should be loaded lazily (on-demand).
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

}

