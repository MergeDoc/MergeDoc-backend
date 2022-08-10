package com.mergedoc.backend.filemanage.dto;

import com.mergedoc.backend.dir.entity.FileType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindFilesInDIRForm {
    @NotNull
    private Long dirId;

    private FileType type;
    private Long memberId;
}
