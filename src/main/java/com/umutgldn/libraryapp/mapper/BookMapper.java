package com.umutgldn.libraryapp.mapper;

import com.umutgldn.libraryapp.dto.googlebooks.GoogleBooksResponse;
import com.umutgldn.libraryapp.dto.googlebooks.GoogleSearchResponseDto;
import com.umutgldn.libraryapp.dto.request.BookRequestDto;
import com.umutgldn.libraryapp.dto.response.BookInPublisherDto;
import com.umutgldn.libraryapp.dto.response.BookResponseDto;
import com.umutgldn.libraryapp.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BookMapper {

    private static final String ISBN_13 = "ISBN_13";


    public BookResponseDto toResponseDto(Book book) {
        String publisherName=null;
        if (book.getPublisher()!=null) {
            publisherName=book.getPublisher().getPublisherName();
        }
        return  BookResponseDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .isbn13(book.getIsbn13())
                .publicationYear(book.getPublicationYear())
                .publisherName(publisherName)
                .authorNameSurname(extractAuthorFromBook(book))
                .build();

    }

    public BookInPublisherDto toInPublisherDto(Book book) {
        return BookInPublisherDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .isbn13(book.getIsbn13())
                .publicationYear(book.getPublicationYear())
                .authorNameSurname(extractAuthorFromBook(book))
                .build();
    }

    public Book toEntity(BookRequestDto dto){
        Book book = new Book();
        book.setTitle(dto.title());
        book.setPrice(dto.price());
        book.setIsbn13(dto.isbn13());
        book.setPublicationYear(dto.publicationYear());
        return book;
    }

    public GoogleSearchResponseDto fromGoogleBook(GoogleBooksResponse.Item item){
        GoogleBooksResponse.VolumeInfo volumeInfo=item.volumeInfo();
        if (volumeInfo == null) {
            return GoogleSearchResponseDto.builder().build();
        }
        return GoogleSearchResponseDto.builder()
                .title(volumeInfo.title())
                .price(extractPrice(item.saleInfo()))
                .isbn13(extractISBN(volumeInfo.industryIdentifiers()))
                .publisherName(extractPublisher(volumeInfo))
                .authorNameSurname(extractAuthor(volumeInfo.authors()))
                .build();
    }



    private String extractAuthorFromBook(Book book) {
        if (book.getAuthor() == null) {
            return null;
        }
        return book.getAuthor().getAuthorNameSurname();
    }

    private Double extractPrice(GoogleBooksResponse.SaleInfo saleInfo) {
        if (saleInfo == null) return null;
        if (saleInfo.listPrice() == null) return null;

        return saleInfo.listPrice().amount();
    }

    private String extractISBN(List<GoogleBooksResponse.IndustryIdentifier> identifiers) {

        if (identifiers == null || identifiers.isEmpty()) return null;
       return identifiers.stream()
               .filter(id->ISBN_13.equals(id.type()))
               .map(GoogleBooksResponse.IndustryIdentifier::identifier)
               .findFirst()
               .orElse(null);
    }

    private String extractPublisher(GoogleBooksResponse.VolumeInfo volumeInfo) {
      if(volumeInfo == null ||volumeInfo.publisher()==null ||volumeInfo.publisher().isBlank()) {
          return null;
      }
        return volumeInfo.publisher();
    }
    private String extractAuthor(List<String> authors) {
        if (authors == null || authors.isEmpty()) return null;
        return authors.get(0);
    }
}
