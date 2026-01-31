package com.umutgldn.libraryapp.service;

import com.umutgldn.libraryapp.dto.response.PublisherResponseDto;
import com.umutgldn.libraryapp.dto.response.PublisherWithBooksDto;

import java.util.List;

public interface PublisherService {

    List<PublisherResponseDto> getAllPublishers();
    List<PublisherWithBooksDto> getPublishersWithBooksAndAuthors(int limit);
}
