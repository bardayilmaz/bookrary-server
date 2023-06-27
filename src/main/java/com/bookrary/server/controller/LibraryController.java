package com.bookrary.server.controller;

import com.bookrary.server.model.request.LibraryRequest;
import com.bookrary.server.model.response.LibraryResponse;
import com.bookrary.server.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/library")
@RestController
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
    public Page<LibraryResponse> getLibraries(@ApiIgnore Pageable pageable) {
        return libraryService.getLibraries(pageable);
    }

    @GetMapping("/{libraryId}")
    public LibraryResponse getLibraries(@PathVariable String libraryId) {
        return libraryService.getLibrary(libraryId);
    }

    @PostMapping
    public LibraryResponse addLibrary(@RequestBody LibraryRequest libraryRequest) {
        return libraryService.addLibrary(libraryRequest);
    }

    @PutMapping("/{libraryId}")
    public LibraryResponse updateLibrary(@PathVariable String libraryId, @RequestBody LibraryRequest libraryRequest) {
        return libraryService.updateLibrary(libraryRequest, libraryId);
    }

    @DeleteMapping("/{libraryId}")
    public LibraryResponse deleteLibrary(@PathVariable String libraryId) {
        return libraryService.deleteLibrary(libraryId);
    }
}
