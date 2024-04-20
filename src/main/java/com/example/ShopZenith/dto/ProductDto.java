package com.example.ShopZenith.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductDto {
    private Long id;

    private String name;

    private Long price;

    private String description;

    private byte[] byteImg;

    private Long categoryId;   // we need this to link our product with category

    private String categoryName;

    private MultipartFile img;  // we will get the image from the frontend in the multipart, and return it into the byte[]

}
