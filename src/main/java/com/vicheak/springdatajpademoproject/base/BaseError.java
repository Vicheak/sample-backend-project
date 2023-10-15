package com.vicheak.springdatajpademoproject.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseError<T>(String message,
                           Integer code,
                           Boolean status,
                           LocalDateTime localDateTime,
                           T errors) {
}
