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
public class PageInDIR extends InDIR{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dir_id")
    private DIR dir;

//    Page Entity 추가 후 연관관계 추가

    public void setDIR(DIR dir) {
        this.dir = dir;
    }

    @Builder
    public PageInDIR(DIR dir) {
        this.dir = dir;
    }
}
