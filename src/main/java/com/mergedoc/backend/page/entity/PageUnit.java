package com.mergedoc.backend.page.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageUnit {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

// Unit 연관관계 매핑 추가


    @Builder
    public PageUnit(String name, Page page) {
        this.name = name;
        this.page = page;
    }
}
