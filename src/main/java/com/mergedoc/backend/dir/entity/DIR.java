package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    //    member 구현 후 연관관계 셋팅 필요

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private DIR parent;

    @Builder
    public DIR(DIR parent) {
        this.parent = parent;
    }
}
