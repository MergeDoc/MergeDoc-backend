package com.mergedoc.backend.dir.entity;

import com.mergedoc.backend.Base.BaseEntity;
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


    public void setDir(DIR dir) {
        this.dir = dir;
    }

    @Builder
    public UnitInDIR(DIR dir) {
        this.dir = dir;
    }
}
