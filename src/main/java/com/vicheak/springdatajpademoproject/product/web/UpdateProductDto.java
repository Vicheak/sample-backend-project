package com.vicheak.springdatajpademoproject.product.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductDto(@NotBlank @Size(min = 5, max = 255)
                               String name,
                               @NotBlank @Size(min = 5)
                               String description,
                               @NotNull @Positive
                               Integer categoryId) {
}
