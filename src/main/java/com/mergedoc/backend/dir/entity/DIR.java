package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import com.mergedoc.backend.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private DIR parent;

    @Builder
    public DIR(Member member, DIR parent, String name, String path) {
        this.member = member;
        this.parent = parent;
        this.setName(name);
        this.setPath(path);
    }
}
