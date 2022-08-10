package com.mergedoc.backend.share.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSharedPageForm {
    private Long pageId;
    private Long memberId;
}
