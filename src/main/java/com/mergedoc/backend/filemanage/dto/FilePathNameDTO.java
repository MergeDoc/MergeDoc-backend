package com.mergedoc.backend.filemanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilePathNameDTO {
    private String path;
    private String name;
}
