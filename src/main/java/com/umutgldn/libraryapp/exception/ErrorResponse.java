package com.umutgldn.libraryapp.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}
