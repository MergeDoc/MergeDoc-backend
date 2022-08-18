package com.mergedoc.backend.dir.entity;

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

//    Unit Entity 추가 후 연관관계 추가


    @Builder
    public UnitInDIR(DIR dir, Member member, String path, String name) {
        this.setMember(member);
        this.setDir(dir);
        this.setName(name);
        this.setPath(path);
    }
}
