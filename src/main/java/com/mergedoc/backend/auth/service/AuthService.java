package com.mergedoc.backend.auth.service;

import com.mergedoc.backend.auth.dto.SignInForm;
import com.mergedoc.backend.auth.dto.SignUpForm;
import com.mergedoc.backend.exception.NotFoundException;
import com.mergedoc.backend.member.entity.Member;
import com.mergedoc.backend.member.entity.Role;
import com.mergedoc.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private void validateUsernameDuplicated(String username) {
        if(memberRepository.existsByName(username)) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }

    private void validateEmailDuplicated(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    @Transactional
    public Long signUpMember(SignUpForm form) {
        validateEmailDuplicated(form.getEmail());
        validateUsernameDuplicated(form.getUsername());

        Member member = Member.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .passwd(passwordEncoder.encode(form.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(member);

        return member.getId();
    }

    public Member signInMember(SignInForm form) {
        Member member = memberRepository.findByUsername(form.getUsername())
                .orElseThrow(() -> new NotFoundException("계정이 존재하지 않습니다."));

        if (!passwordEncoder.matches(form.getPasswd(), member.getPasswd())) {
            throw new NotFoundException("비밀번호가 잘못되었습니다.");
        }

        return member;
    }
}
