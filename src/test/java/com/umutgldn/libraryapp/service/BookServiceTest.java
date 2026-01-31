package com.umutgldn.libraryapp.service;


import com.umutgldn.libraryapp.dto.response.BookResponseDto;
import com.umutgldn.libraryapp.entity.Author;
import com.umutgldn.libraryapp.entity.Book;
import com.umutgldn.libraryapp.entity.Publisher;
import com.umutgldn.libraryapp.mapper.BookMapper;
import com.umutgldn.libraryapp.repository.BookRepository;
import com.umutgldn.libraryapp.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        Publisher publisher = new Publisher();
        publisher.setPublisherId(1L);
        publisher.setPublisherName("Prentice Hall");

        Author author = new Author();
        author.setAuthorId(1L);
        author.setAuthorNameSurname("Robert C. Martin");

        book1 = new Book();
        book1.setBookId(1L);
        book1.setTitle("Clean Code");
        book1.setPrice(45.0);
        book1.setIsbn13("9780132350884");
        book1.setPublicationYear(2008);
        book1.setPublisher(publisher);
        book1.setAuthor(author);

        book2 = new Book();
        book2.setBookId(2L);
        book2.setTitle("Atomic Habits");
        book2.setPrice(35.0);
        book2.setPublicationYear(2024);
        book2.setPublisher(publisher);
        book2.setAuthor(author);

        book3 = new Book();
        book3.setBookId(3L);
        book3.setTitle("Effective Java");
        book3.setPrice(55.0);
        book3.setPublicationYear(2018);
        book3.setPublisher(publisher);
        book3.setAuthor(author);
    }

    @Test
    @DisplayName("Should return books starting with given letter")
    void getBooksStartingWith_ShouldReturnFilteredBooks() {
        List<Book> allBooks = Arrays.asList(book1, book2, book3);

        when(bookRepository.findAllWithAuthorsAndPublisher()).thenReturn(allBooks);

        BookResponseDto dto1 = BookResponseDto.builder()
                .bookId(1L)
                .title("Clean Code")
                .build();

        when(bookMapper.toResponseDto(book1)).thenReturn(dto1);

        List<BookResponseDto> result = bookService.getBooksStartingWith("C");

        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).title());
        verify(bookRepository, times(1)).findAllWithAuthorsAndPublisher();
    }

    @Test
    @DisplayName("Should return books published after given year")
    void getBooksPublishedAfterYear_ShouldReturnFilteredBooks() {
        List<Book> booksAfter2023 = List.of(book2);
        when(bookRepository.findByPublicationYear(2023)).thenReturn(booksAfter2023);

        BookResponseDto dto = BookResponseDto.builder()
                .bookId(2L)
                .title("Atomic Habits")
                .publicationYear(2024)
                .build();

        when(bookMapper.toResponseDto(book2)).thenReturn(dto);

        List<BookResponseDto> result = bookService.getBooksPublishedAfterYear(2023);

        assertEquals(1, result.size());
        assertEquals(2024, result.get(0).publicationYear());
        verify(bookRepository, times(1)).findByPublicationYear(2023);
    }
}
