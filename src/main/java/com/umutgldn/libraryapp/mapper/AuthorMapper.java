package com.umutgldn.libraryapp.mapper;

import com.umutgldn.libraryapp.dto.response.AuthorResponseDto;
import com.umutgldn.libraryapp.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponseDto toResponseDto(Author author){
        return AuthorResponseDto.builder()
                .authorId(author.getAuthorId())
                .authorNameSurname(author.getAuthorNameSurname())
                .build();
    }
}
