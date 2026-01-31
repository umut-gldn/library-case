package com.umutgldn.libraryapp.dto.response;

import lombok.Builder;

@Builder
public record PublisherResponseDto(
        Long publisherId,
        String publisherName
) {}
