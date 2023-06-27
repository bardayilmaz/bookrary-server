package com.bookrary.server.controller;

import com.bookrary.server.entity.Author;
import com.bookrary.server.model.response.AuthorResponse;
import com.bookrary.server.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/most-sold")
    public List<AuthorResponse> getMostSoldAuthors() {
        return authorService.getMostSoldAuthors();
    }

}
