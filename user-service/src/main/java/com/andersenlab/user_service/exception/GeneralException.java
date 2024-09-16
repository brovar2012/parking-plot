package com.andersenlab.user_service.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralException extends RuntimeException {

    String message;

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
