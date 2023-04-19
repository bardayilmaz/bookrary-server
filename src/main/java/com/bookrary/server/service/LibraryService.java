package com.bookrary.server.service;

import com.bookrary.server.entity.Library;
import com.bookrary.server.model.request.LibraryRequest;
import com.bookrary.server.model.response.LibraryResponse;
import com.bookrary.server.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryResponse addLibrary(LibraryRequest libraryRequest) {
        Library library = fromRequest(new Library(), libraryRequest);
        return null;
    }

    private Library fromRequest(Library newLibrary, LibraryRequest libraryRequest) {
        newLibrary.setName(libraryRequest.getName());
        newLibrary.setAddress(libraryRequest.getAddress());
        return newLibrary;
    }
}
