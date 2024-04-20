package com.example.ShopZenith.repository;

import com.example.ShopZenith.entity.Order;
import com.example.ShopZenith.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    // UserId , OrderStatus ye fields hamari order ke entity mein isi naam se hone chahiye
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
