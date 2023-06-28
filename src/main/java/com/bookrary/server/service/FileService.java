package com.bookrary.server.service;

import com.bookrary.server.config.CdnConfig;
import com.bookrary.server.entity.File;
import com.bookrary.server.entity.User;
import com.bookrary.server.exception.BusinessException;
import com.bookrary.server.exception.ErrorCode;
import com.bookrary.server.model.response.FileResponse;
import com.bookrary.server.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final CdnConfig cdnConfig;

    @SneakyThrows
    public FileResponse uploadFile(MultipartFile multipartFile, Optional<User> authenticatedUserOptional) {
        User authenticatedUser = authenticatedUserOptional
                .orElseThrow(() -> new BusinessException("User not found", ErrorCode.forbidden));

        String url = cdnConfig.getUploadPath() + "/" + authenticatedUser.getId() + "-" + multipartFile.getName();

        multipartFile.transferTo(new java.io.File(url));

        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setPath(url);
        file.setUploaderId(authenticatedUser.getId());
        file.setContentType(multipartFile.getContentType());
        fileRepository.save(file);

        return FileResponse.fromEntity(file);
    }

    @SneakyThrows
    public ResponseEntity<?> getFile(String id) {
        File file;
        file = fileRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Resource does not exist!", ErrorCode.resource_missing));
        String path = file.getPath();
        byte[] fileInByte = Files.readAllBytes(new java.io.File(path).toPath());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(file.getContentType()))
                .body(fileInByte);
    }
}
