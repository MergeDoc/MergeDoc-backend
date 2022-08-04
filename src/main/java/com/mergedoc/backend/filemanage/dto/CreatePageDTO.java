package com.mergedoc.backend.filemanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePageDTO {
    private String dirPath;
    private String dirName;
    private String name;

    public String getPath() {
        return dirPath + dirName + "/";
    }
}
