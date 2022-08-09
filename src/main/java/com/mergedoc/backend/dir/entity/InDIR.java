package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class InDIR extends BaseEntity {
    private String path;
    private String name;

    protected void setPath(String path) {
        this.path = path;
    }
    protected void setName(String name) {
        this.name = name;
    }
}
