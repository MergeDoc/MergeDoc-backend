package com.mergedoc.backend.unit.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnitTag extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "unit_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public UnitTag(Unit unit, Tag tag){
        this.unit = unit;
        this.tag = tag;
    }
}
