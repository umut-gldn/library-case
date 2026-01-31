package com.umutgldn.libraryapp.dto.googlebooks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GoogleSearchResponseDto(
        String title,
        Double price,
        @JsonProperty("ISBN13") String isbn13,
        String publisherName,
        String authorNameSurname
) {}
