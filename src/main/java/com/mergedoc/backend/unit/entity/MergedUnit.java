package com.mergedoc.backend.unit.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MergedUnit extends Unit{

    @OneToMany(mappedBy = "mergedUnit", cascade = CascadeType.ALL)
    private List<MappedUnit> mappedUnitList = new ArrayList<>();

    @Builder
    public MergedUnit(String title){
        this.setTitle(title);
        this.setForm(Form.Merged);
    }

    //==연관관계 편의 메서드==//
    public void addMappedUnit(MappedUnit mappedUnit){
        mappedUnitList.add(mappedUnit);
        mappedUnit.builder().mergedUnit(this);
    }

}
