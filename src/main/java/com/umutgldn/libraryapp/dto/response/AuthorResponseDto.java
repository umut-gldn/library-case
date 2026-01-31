package com.umutgldn.libraryapp.dto.response;

import lombok.Builder;

@Builder
public record AuthorResponseDto(
        Long authorId,
        String authorNameSurname
) {
}
