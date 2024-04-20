package com.example.ShopZenith.services.customer.cart;

import com.example.ShopZenith.dto.AddProductInCartDto;
import com.example.ShopZenith.dto.CartItemsDto;
import com.example.ShopZenith.dto.OrderDto;
import com.example.ShopZenith.entity.CartItems;
import com.example.ShopZenith.entity.Order;
import com.example.ShopZenith.entity.Product;
import com.example.ShopZenith.entity.User;
import com.example.ShopZenith.enums.OrderStatus;
import com.example.ShopZenith.repository.CartItemRepository;
import com.example.ShopZenith.repository.OrderRepository;
import com.example.ShopZenith.repository.ProductRepository;
import com.example.ShopZenith.repository.UserRepository;
import com.example.ShopZenith.transformer.CartTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        // get the order based on user id , and its status
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.PENDING);

        Optional<CartItems> optionalCartItems =cartItemRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId()
                ,activeOrder.getId(),addProductInCartDto.getUserId());

        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent() && optionalProduct.isPresent()){
                CartItems cart = CartItems.builder()
                        .product(optionalProduct.get())
                        .price(optionalProduct.get().getPrice())
                        .quantity(1L)   // whenever we try to add the product in the cart for the first time , it's value will one
                        .user(optionalUser.get())
                        .order(activeOrder)
                        .build();

                // save the cartItem
                CartItems saveCartItem = cartItemRepository.save(cart);

//                // now update the orderDetails
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);  // add the cart to cartItems
//
//                // save the order
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(saveCartItem);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING);

        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartTransformer ::cartToCartItemDto).collect(Collectors.toList());
        return  OrderDto.builder()
                .amount(activeOrder.getAmount())
                .id(activeOrder.getId())
                .orderStatus(activeOrder.getOrderStatus())
                .discount(activeOrder.getDiscount())
                .totalAmount(activeOrder.getTotalAmount())
                .cartItems(cartItemsDtoList)
                .build();

    }
}
