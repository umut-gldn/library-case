package com.umutgldn.libraryapp.repository;

import com.umutgldn.libraryapp.entity.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

    Optional<Publisher> findByPublisherName(String publisherName);

    @Query("""
             SELECT DISTINCT p FROM Publisher p
            LEFT JOIN FETCH p.books b
            LEFT JOIN FETCH b.author
            """)
    List<Publisher> findAllWithBooksAndAuthors(Pageable pageable);
}
