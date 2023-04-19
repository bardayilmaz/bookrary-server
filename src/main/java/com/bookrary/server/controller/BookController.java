package com.bookrary.server.controller;

import com.bookrary.server.model.request.BookRequest;
import com.bookrary.server.model.response.BookResponse;
import com.bookrary.server.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Page<BookResponse> getBooks(@ApiIgnore Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @GetMapping("/{bookId}")
    public BookResponse getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    public BookResponse addBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @PutMapping("/{bookId}")
    public BookResponse updateBook(@RequestBody @Valid BookRequest bookRequest, @PathVariable String bookId) {
        return bookService.updateBook(bookRequest, bookId);
    }
}
