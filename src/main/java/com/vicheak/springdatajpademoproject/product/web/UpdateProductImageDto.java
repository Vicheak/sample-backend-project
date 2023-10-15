package com.vicheak.springdatajpademoproject.product.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProductImageDto(@NotBlank @Size(min = 5) String image) {
}
