package com.mergedoc.backend.share.entity;

import com.mergedoc.backend.Base.BaseEntity;

import com.mergedoc.backend.member.entity.Member;
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

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public SharedPage(String name, String url, LocalDateTime expiredDate, Page page, Member member) {
        this.name = name;
        this.url = url;
        this.expiredDate = expiredDate;
        this.page = page;
        this.member = member;
    }
}