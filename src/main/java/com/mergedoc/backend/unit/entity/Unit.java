package com.mergedoc.backend.unit.entity;

import com.mergedoc.backend.Base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Unit extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "unit_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Form form;

    @Enumerated(EnumType.STRING)
    private Type type;

}