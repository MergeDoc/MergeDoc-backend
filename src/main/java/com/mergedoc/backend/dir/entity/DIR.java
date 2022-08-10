package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    @Builder
    public DIR(Member member, DIR parent, String name, String path) {
        this.setMember(member);
        this.setDir(parent);
        this.setName(name);
        this.setPath(path);
    }
}
