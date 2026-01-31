package com.umutgldn.libraryapp.controller;

import com.umutgldn.libraryapp.dto.googlebooks.GoogleSearchResponseDto;
import com.umutgldn.libraryapp.dto.request.BookRequestDto;
import com.umutgldn.libraryapp.dto.response.BookResponseDto;
import com.umutgldn.libraryapp.service.BookService;
import com.umutgldn.libraryapp.service.GoogleBooksService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final GoogleBooksService googleBooksService;

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(summary = "Get book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookRequestDto));
    }

    @Operation(summary = "Update book by ID")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequestDto));
    }

    @Operation(summary = "Delete book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
    }

    @Operation(summary = "Get books starting with a letter (Stream)")
    @GetMapping("/starting-with")
    public ResponseEntity<List<BookResponseDto>> getBooksStartingWith(
            @RequestParam(defaultValue = "A") String letter) {
        return ResponseEntity.ok(bookService.getBooksStartingWith(letter));
    }

    @Operation(summary = "Get books published after year (JPA Query)")
    @GetMapping("/after-year")
    public ResponseEntity<List<BookResponseDto>> getBooksAfterYear(
            @RequestParam(defaultValue = "2023") Integer year
    ) {
        return ResponseEntity.ok(bookService.getBooksPublishedAfterYear(year));
    }


    @Operation(summary = "Search books from Google Books API")
    @GetMapping("/search")
    public ResponseEntity<List<GoogleSearchResponseDto>> searchGoogleBooks(
            @RequestParam String title) {
        return ResponseEntity.ok(googleBooksService.searchBooks(title));
    }
}
