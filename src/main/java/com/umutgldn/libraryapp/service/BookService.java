package com.umutgldn.libraryapp.service;

import com.umutgldn.libraryapp.dto.request.BookRequestDto;
import com.umutgldn.libraryapp.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {

    List<BookResponseDto> getAllBooks();

    BookResponseDto getBookById(Long id);

    BookResponseDto createBook(BookRequestDto bookRequestDto);

    BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto);

    void deleteBook(Long id);

    List<BookResponseDto> getBooksStartingWith(String letter);

    List<BookResponseDto> getBooksPublishedAfterYear(Integer year);
}
