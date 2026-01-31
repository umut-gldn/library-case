package com.umutgldn.libraryapp.repository;

import com.umutgldn.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn13(String isbn13);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.publisher WHERE b.publicationYear > :year")
    List<Book> findByPublicationYear(@Param("year") Integer year);

    @Query("SELECT b FROM Book  b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.publisher")
    List<Book> findAllWithAuthorsAndPublisher();

}
