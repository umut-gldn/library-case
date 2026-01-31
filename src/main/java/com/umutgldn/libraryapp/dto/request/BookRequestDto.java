package com.umutgldn.libraryapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record BookRequestDto(
        @NotBlank(message = "Title is required")
        String title,

        @Positive(message = "Price must be positive")
        Double price,

        @JsonProperty("ISBN13")
        String isbn13,

        @NotNull(message = "Publication year is required")
        @Min(value = 1400)
        @Max(value = 2026)
        Integer publicationYear,

        @NotBlank(message = "Publisher name is required")
        String publisherName,

        @NotBlank(message = "Author name is required")
        String authorNameSurname
) {
}
