package com.umutgldn.libraryapp.service.impl;

import com.umutgldn.libraryapp.dto.response.AuthorResponseDto;
import com.umutgldn.libraryapp.mapper.AuthorMapper;
import com.umutgldn.libraryapp.repository.AuthorRepository;
import com.umutgldn.libraryapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;


    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDto)
                .toList();
    }
}
