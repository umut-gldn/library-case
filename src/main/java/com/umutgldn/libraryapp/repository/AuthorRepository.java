package com.umutgldn.libraryapp.repository;

import com.umutgldn.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByAuthorNameSurname(String authorNameSurname);
}
