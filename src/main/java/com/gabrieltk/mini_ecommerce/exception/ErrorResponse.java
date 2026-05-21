package com.gabrieltk.mini_ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer error;
    private String message;
}
