package com.umutgldn.libraryapp.dto.googlebooks;

import java.util.List;

public record GoogleBooksResponse(List<Item> items) {

    public record Item(
            VolumeInfo volumeInfo,
            SaleInfo saleInfo
    ) {}

    public record  VolumeInfo(
            String title,
            List<String> authors,
            String publisher,
            String publishedDate,
            List<IndustryIdentifier> industryIdentifiers
    ){}

    public record IndustryIdentifier(
            String type,
            String identifier
    ){}

    public record SaleInfo(
            ListPrice listPrice
    ){}

    public record ListPrice(
            Double amount
    ){}

}
