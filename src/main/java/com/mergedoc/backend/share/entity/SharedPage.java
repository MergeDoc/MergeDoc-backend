package com.mergedoc.backend.share.entity;

import com.mergedoc.backend.Base.BaseEntity;

import com.mergedoc.backend.page.entity.Page;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SharedPage extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String url;

    private LocalDateTime expiredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

//    Member entity 추가 후 연관관계 추가

    @Builder
    public SharedPage(String name, String url, LocalDateTime expiredDate, Page page) {
        this.name = name;
        this.url = url;
        this.expiredDate = expiredDate;
        this.page = page;
    }
}