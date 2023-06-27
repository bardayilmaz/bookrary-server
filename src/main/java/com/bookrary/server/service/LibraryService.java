package com.bookrary.server.service;

import com.bookrary.server.entity.Library;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.LibraryRequest;
import com.bookrary.server.model.response.LibraryResponse;
import com.bookrary.server.repository.LibraryRepository;
import com.bookrary.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;

    public Page<LibraryResponse> getLibraries(Pageable pageable) {
        return libraryRepository.findAll(pageable).map(LibraryResponse::fromEntity);
    }

    public LibraryResponse getLibrary(String libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new BusinessException("Library not found", ErrorCode.resource_missing));
        return LibraryResponse.fromEntity(library);
    }

    public LibraryResponse addLibrary(LibraryRequest libraryRequest) {
        Library library = fromRequest(new Library(), libraryRequest);
        libraryRepository.save(library);
        return LibraryResponse.fromEntity(library);
    }

    public LibraryResponse updateLibrary(LibraryRequest libraryRequest, String libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new BusinessException("Library not found", ErrorCode.resource_missing));
        fromRequest(library, libraryRequest);
        return LibraryResponse.fromEntity(library);
    }

    public LibraryResponse deleteLibrary(String libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new BusinessException("Library not found", ErrorCode.resource_missing));
        if(userRepository.existsByLibrary(library)) {
            throw new BusinessException("There are users in library", ErrorCode.validation);
        }
        libraryRepository.delete(library);
        return LibraryResponse.fromEntity(library);
    }

    private Library fromRequest(Library newLibrary, LibraryRequest libraryRequest) {
        newLibrary.setName(libraryRequest.getName());
        newLibrary.setAddress(libraryRequest.getAddress());
        return newLibrary;
    }
}
