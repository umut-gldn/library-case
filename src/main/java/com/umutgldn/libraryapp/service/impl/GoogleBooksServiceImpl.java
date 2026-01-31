package com.umutgldn.libraryapp.service.impl;

import com.umutgldn.libraryapp.client.GoogleBooksClient;
import com.umutgldn.libraryapp.dto.googlebooks.GoogleBooksResponse;
import com.umutgldn.libraryapp.dto.googlebooks.GoogleSearchResponseDto;
import com.umutgldn.libraryapp.mapper.BookMapper;
import com.umutgldn.libraryapp.service.GoogleBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleBooksServiceImpl implements GoogleBooksService {
    private final GoogleBooksClient googleBooksClient;
    private final BookMapper bookMapper;


    @Override
    public List<GoogleSearchResponseDto> searchBooks(String title) {
        GoogleBooksResponse response = googleBooksClient.searchBooks(title);

        if (response == null || response.items() == null || response.items().isEmpty()) {
            return Collections.emptyList();
        }
        return response.items().stream()
                .map(bookMapper::fromGoogleBook)
                .toList();
    }
}
