package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class InDIR extends BaseEntity {
    private String path;
    private String name;

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }
}
