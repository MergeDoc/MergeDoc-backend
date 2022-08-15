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
public class Tag extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String content;

    private int count;

    @Builder
    public Tag(String content, int count) {
        this.content = content;
        this.count = count;
    }
}