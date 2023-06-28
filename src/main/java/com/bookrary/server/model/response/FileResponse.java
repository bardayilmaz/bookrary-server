package com.bookrary.server.model.response;

import com.bookrary.server.entity.File;
import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FileResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String name;
    private String path;
    private String contentType;
    private String uploaderId;

    public static FileResponse fromEntity(File file) {
        return FileResponse.builder()
                .id(file.getId())
                .created(file.getCreated())
                .updated(file.getUpdated())
                .name(file.getName())
                .path(file.getPath())
                .contentType(file.getContentType())
                .uploaderId(file.getUploaderId())
                .build();
    }
}
