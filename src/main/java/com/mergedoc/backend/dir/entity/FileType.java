package com.mergedoc.backend.dir.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FileType {
    DIR, PAGE, UNIT;

    @JsonCreator
    public static FileType from(String s) { return FileType.valueOf(s); }
}
