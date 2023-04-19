package com.bookrary.server.service;

import com.bookrary.server.entity.Book;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.BookRequest;
import com.bookrary.server.model.response.BookResponse;
import com.bookrary.server.repository.AuthorRepository;
import com.bookrary.server.repository.BookRepository;
import com.bookrary.server.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SaleRepository saleRepository;

    public Page<BookResponse> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookResponse::fromEntity);
    }

    public BookResponse getBook(String bookId) {
        return BookResponse.fromEntity(bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book not found", ErrorCode.resource_missing)));
    }

    public BookResponse addBook(BookRequest bookRequest) {

        Book book = fromRequest(new Book(), bookRequest);

        return BookResponse.fromEntity(book);
    }

    public BookResponse updateBook(BookRequest bookRequest, String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book not found.", ErrorCode.resource_missing));

        if(saleRepository.existsByBook(book)) {
            throw new BusinessException("Sold books can not be updated", ErrorCode.validation);
        }

        fromRequest(book, bookRequest);
        return BookResponse.fromEntity(book);
    }

    private Book fromRequest(Book newBook, BookRequest bookRequest) {
        newBook.setLanguage(bookRequest.getLanguage());
        newBook.setAuthor(authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new BusinessException("Author not found", ErrorCode.resource_missing)));
        newBook.setType(bookRequest.getType());
        newBook.setTitle(bookRequest.getTitle());
        newBook.setPageCount(bookRequest.getPageCount());
        newBook.setPublisherName(bookRequest.getPublisherName());
        newBook.setPublicationDate(bookRequest.getPublicationDate());

        return newBook;
    }


}
