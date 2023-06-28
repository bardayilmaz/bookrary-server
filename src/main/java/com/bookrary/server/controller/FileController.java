package com.bookrary.server.controller;

import com.bookrary.server.model.response.FileResponse;
import com.bookrary.server.service.AuthenticationService;
import com.bookrary.server.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file, authenticationService.getAuthenticatedUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable String id) {
        return fileService.getFile(id);
    }
}
