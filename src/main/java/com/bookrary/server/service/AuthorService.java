package com.bookrary.server.service;

import com.bookrary.server.entity.Author;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.request.AuthorRequest;
import com.bookrary.server.model.response.AuthorResponse;
import com.bookrary.server.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<AuthorResponse> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(AuthorResponse::fromEntity);
    }

    public AuthorResponse getAuthor(String authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new BusinessException("Author not found", ErrorCode.resource_missing));
        return AuthorResponse.fromEntity(author);
    }

    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = fromRequest(new Author(), authorRequest);
        authorRepository.save(author);
        return AuthorResponse.fromEntity(author);
    }

    public AuthorResponse updateAuthor(String id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Author not found", ErrorCode.resource_missing));
        fromRequest(author, authorRequest);
        authorRepository.save(author);
        return AuthorResponse.fromEntity(author);
    }

    public List<AuthorResponse> getMostSoldAuthors() {
        return authorRepository.getMostSoldAuthors().stream().map(AuthorResponse::fromEntity).collect(Collectors.toList());
    }

    private Author fromRequest(Author author, AuthorRequest authorRequest) {
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(author.getLastName());
        return author;
    }
}
