package com.umutgldn.libraryapp.service.impl;

import com.umutgldn.libraryapp.dto.request.BookRequestDto;
import com.umutgldn.libraryapp.dto.response.BookResponseDto;
import com.umutgldn.libraryapp.entity.Author;
import com.umutgldn.libraryapp.entity.Book;
import com.umutgldn.libraryapp.entity.Publisher;
import com.umutgldn.libraryapp.exception.DuplicateResourceException;
import com.umutgldn.libraryapp.exception.ResourceNotFoundException;
import com.umutgldn.libraryapp.mapper.BookMapper;
import com.umutgldn.libraryapp.repository.AuthorRepository;
import com.umutgldn.libraryapp.repository.BookRepository;
import com.umutgldn.libraryapp.repository.PublisherRepository;
import com.umutgldn.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAllWithAuthorsAndPublisher()
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }


    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return bookMapper.toResponseDto(book);
    }

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        if (bookRequestDto.isbn13() != null) {
            bookRepository.findByIsbn13(bookRequestDto.isbn13())
                    .ifPresent(book -> {
                        throw new DuplicateResourceException("Book already exists with ISBN: " + bookRequestDto.isbn13());
                    });
        }
        Publisher publisher = findOrCreatePublisher(bookRequestDto.publisherName());
        Author author = findOrCreateAuthor(bookRequestDto.authorNameSurname());

        Book book = bookMapper.toEntity(bookRequestDto);
        book.setPublisher(publisher);
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        Publisher publisher = findOrCreatePublisher(bookRequestDto.publisherName());
        Author author = findOrCreateAuthor(bookRequestDto.authorNameSurname());

        book.setTitle(bookRequestDto.title());
        book.setPrice(bookRequestDto.price());
        book.setIsbn13(bookRequestDto.isbn13());
        book.setPublicationYear(bookRequestDto.publicationYear());
        book.setPublisher(publisher);
        book.setAuthor(author);

        return bookMapper.toResponseDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> getBooksStartingWith(String letter) {
        return bookRepository.findAllWithAuthorsAndPublisher()
                .stream()
                .filter(book -> book.getTitle().toUpperCase().startsWith(letter.toUpperCase()))
                .map(bookMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getBooksPublishedAfterYear(Integer year) {
        return bookRepository.findByPublicationYear(year)
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }


    private Publisher findOrCreatePublisher(String publisherName) {
        return publisherRepository.findByPublisherName(publisherName)
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher();
                    newPublisher.setPublisherName(publisherName);
                    return publisherRepository.save(newPublisher);
                });
    }

    private Author findOrCreateAuthor(String authorName) {
        return authorRepository.findByAuthorNameSurname(authorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAuthorNameSurname(authorName);
                    return authorRepository.save(newAuthor);
                });
    }
}
