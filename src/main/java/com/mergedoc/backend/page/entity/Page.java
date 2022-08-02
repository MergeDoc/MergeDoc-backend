package com.mergedoc.backend.page.entity;

import com.mergedoc.backend.Base.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Page extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Builder
    public Page(String name) {
        this.name = name;
    }
}
