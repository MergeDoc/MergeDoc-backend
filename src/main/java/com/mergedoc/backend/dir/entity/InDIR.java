package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import com.mergedoc.backend.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public abstract class InDIR extends BaseEntity {
    private String path;
    private String name;
    private FileType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private DIR dir;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected void setDir(DIR dir) {
        this.dir = dir;
    }
    protected void setMember(Member member) {this.member = member;}
    protected void setPath(String path) {
        this.path = path;
    }
    protected void setName(String name) {
        this.name = name;
    }
}
