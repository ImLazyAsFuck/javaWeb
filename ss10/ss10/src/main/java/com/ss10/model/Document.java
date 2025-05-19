package com.ss10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document{
    private int id;
    private String title;
    private String description;
    private MultipartFile file;
    private String fileUrl;
}
