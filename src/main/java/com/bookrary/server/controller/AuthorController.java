package com.bookrary.server.controller;

import com.bookrary.server.model.request.AuthorRequest;
import com.bookrary.server.model.response.AuthorResponse;
import com.bookrary.server.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public Page<AuthorResponse> getAuthors(@ApiIgnore Pageable pageable) {
        return authorService.getAuthors(pageable);
    }

    @PostMapping
    public AuthorResponse addAuthor(@RequestBody AuthorRequest authorRequest) {
        return authorService.addAuthor(authorRequest);
    }

    @PutMapping("/{authorId}")
    public AuthorResponse updateAuthor(@PathVariable String authorId, @RequestBody AuthorRequest authorRequest) {
        return authorService.updateAuthor(authorId, authorRequest);
    }

    @GetMapping("/most-sold")
    public List<AuthorResponse> getMostSoldAuthors() {
        return authorService.getMostSoldAuthors();
    }

}
