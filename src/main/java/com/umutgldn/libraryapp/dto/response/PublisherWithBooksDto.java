package com.umutgldn.libraryapp.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PublisherWithBooksDto(
        Long publisherId,
        String publisherName,
        List<BookInPublisherDto> books
) {
}
