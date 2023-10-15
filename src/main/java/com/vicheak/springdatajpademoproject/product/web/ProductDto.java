package com.vicheak.springdatajpademoproject.product.web;

public record ProductDto(String uuid,
                         String code,
                         String name,
                         String description,
                         String image,
                         CategoryDto category) {
}
