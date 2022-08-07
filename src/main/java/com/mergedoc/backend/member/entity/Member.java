package com.mergedoc.backend.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String username;
    private String passwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String username, String passwd, Role role) {
        this.email = email;
        this.username = username;
        this.passwd = passwd;
        this.role = role;
    }
}
