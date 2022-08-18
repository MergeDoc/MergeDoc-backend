package com.mergedoc.backend.dir.entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @Builder
    public PageInDIR(DIR dir, Page page, Member member, String path, String name) {
        this.page = page;
        this.setMember(member);
        this.setDir(dir);
        this.setName(name);
        this.setPath(path);
    }
}
