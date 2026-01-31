package com.umutgldn.libraryapp.mapper;

import com.umutgldn.libraryapp.dto.response.BookInPublisherDto;
import com.umutgldn.libraryapp.dto.response.PublisherResponseDto;
import com.umutgldn.libraryapp.dto.response.PublisherWithBooksDto;
import com.umutgldn.libraryapp.entity.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherMapper {
    private final BookMapper bookMapper;

    public PublisherResponseDto toResponseDto(Publisher publisher) {
        return PublisherResponseDto.builder()
                .publisherId(publisher.getPublisherId())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    public PublisherWithBooksDto toWithBooksDto(Publisher publisher) {

        List<BookInPublisherDto> books = publisher.getBooks()
                .stream()
                .map(bookMapper::toInPublisherDto)
                .toList();
        return PublisherWithBooksDto.builder()
                .publisherId(publisher.getPublisherId())
                .publisherName(publisher.getPublisherName())
                .books(books)
                .build();
    }

}
