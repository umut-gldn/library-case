package com.umutgldn.libraryapp.service;

import com.umutgldn.libraryapp.dto.googlebooks.GoogleSearchResponseDto;

import java.util.List;

public interface GoogleBooksService {
    List<GoogleSearchResponseDto> searchBooks(String title);
}
