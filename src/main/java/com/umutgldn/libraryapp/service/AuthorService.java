package com.umutgldn.libraryapp.service;

import com.umutgldn.libraryapp.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> getAllAuthors();
}
