package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import com.mergedoc.backend.member.entity.Member;
import com.mergedoc.backend.page.entity.Page;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageInDIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dir_id")
    private DIR dir;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public PageInDIR(DIR dir, Page page, Member member, String path, String name) {
        this.dir = dir;
        this.page = page;
        this.member = member;
        this.setName(name);
        this.setPath(path);
    }
}
