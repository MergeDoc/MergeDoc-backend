package com.mergedoc.backend.security.details;

import com.mergedoc.backend.member.entity.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberDetails extends User {

    public MemberDetails(Member member) {
        super(member.getEmail(),
                member.getPasswd(),
                AuthorityUtils.createAuthorityList(member.getRole().toString()));
    }

    public String getEmail() {
        return this.getUsername();
    }
}
