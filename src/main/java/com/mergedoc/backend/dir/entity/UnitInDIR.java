package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import com.mergedoc.backend.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnitInDIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dir_id")
    private DIR dir;

//    Unit Entity 추가 후 연관관계 추가

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public UnitInDIR(DIR dir, Member member, String path, String name) {
        this.dir = dir;
        this.member = member;
        this.setName(name);
        this.setPath(path);
    }
}
