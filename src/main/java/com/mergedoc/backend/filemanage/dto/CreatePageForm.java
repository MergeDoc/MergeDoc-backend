package com.mergedoc.backend.filemanage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePageForm {
    @NotNull
    private Long dirId;
    @NotNull
    private String name;

    private Long memberId;
}
