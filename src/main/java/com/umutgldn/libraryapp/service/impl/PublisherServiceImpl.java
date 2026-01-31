package com.umutgldn.libraryapp.service.impl;


import com.umutgldn.libraryapp.dto.response.PublisherResponseDto;
import com.umutgldn.libraryapp.dto.response.PublisherWithBooksDto;
import com.umutgldn.libraryapp.mapper.PublisherMapper;
import com.umutgldn.libraryapp.repository.PublisherRepository;
import com.umutgldn.libraryapp.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherResponseDto> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<PublisherWithBooksDto> getPublishersWithBooksAndAuthors(int limit) {
        Pageable  pageable = PageRequest.of(0, limit);

        return publisherRepository.findAllWithBooksAndAuthors(pageable)
                .stream()
                .map(publisherMapper::toWithBooksDto)
                .toList();
    }
}
