package com.mergedoc.backend.filemanage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPageForm {
    @NotNull
    private Long pageId;
    @NotNull
    private Long memberId;
}
