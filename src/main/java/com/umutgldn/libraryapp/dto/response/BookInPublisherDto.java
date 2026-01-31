package com.umutgldn.libraryapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record BookInPublisherDto(
        Long bookId,
        String title,
        Double price,
        @JsonProperty("ISBN13") String isbn13,
        Integer publicationYear,
        String authorNameSurname
) {

}
