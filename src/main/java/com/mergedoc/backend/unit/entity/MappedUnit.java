package com.mergedoc.backend.unit.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MappedUnit extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "mapped_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Form form;

    private int orders;

    @Builder
    public MappedUnit( String title, Form form, int orders, DoubleUnit doubleUnit, MergedUnit mergedUnit, Unit originUnit) {
        this.title = title;
        this.form = form;
        this.orders = orders;
        this.doubleUnit = doubleUnit;
        this.mergedUnit = mergedUnit;
        this.originUnit = originUnit;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "double_id")
    private DoubleUnit doubleUnit;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "merged_id")
    private MergedUnit mergedUnit;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Unit originUnit; //single, double

/*
    //==연관관계 편의 메서드==//
    public void addDoubleUnit(DoubleUnit doubleUnit){
        this.doubleUnit = doubleUnit;
        doubleUnit.getMappedUnitList().add(this);
    }

    public void addMergedUnit(MergedUnit mergedUnit){
        this.mergedUnit = mergedUnit;
        mergedUnit.getMappedUnitList().add(this);
    }
*/

}