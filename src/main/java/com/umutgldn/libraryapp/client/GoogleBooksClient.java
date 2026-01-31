package com.umutgldn.libraryapp.client;

import com.umutgldn.libraryapp.dto.googlebooks.GoogleBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "google-books", url = "${google.books.api-url}")
public interface GoogleBooksClient {

    @GetMapping("/volumes")
    GoogleBooksResponse searchBooks(@RequestParam("q") String query);
}
