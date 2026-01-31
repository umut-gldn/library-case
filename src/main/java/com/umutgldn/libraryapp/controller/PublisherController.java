package com.umutgldn.libraryapp.controller;

import com.umutgldn.libraryapp.dto.response.PublisherResponseDto;
import com.umutgldn.libraryapp.dto.response.PublisherWithBooksDto;
import com.umutgldn.libraryapp.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @Operation(summary = "Get all publishers")
    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>>  getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @Operation(summary = "Get publishers with books and authors")
    @GetMapping("/with-books")
    public ResponseEntity<List<PublisherWithBooksDto>>  getAllPublishersWithBooks(
            @RequestParam(defaultValue = "2") int limit) {
        return ResponseEntity.ok(publisherService.getPublishersWithBooksAndAuthors(limit));
    }
}
