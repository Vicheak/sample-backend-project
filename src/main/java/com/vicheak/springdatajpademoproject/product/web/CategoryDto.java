package com.vicheak.springdatajpademoproject.product.web;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(@NotBlank(message = "Field name is required!")
                          String name,
                          @NotBlank(message = "Field description is required!")
                          String description) {
}
