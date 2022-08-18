package com.mergedoc.backend.member.repository;

import com.mergedoc.backend.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) { em.persist(member); }

    public Member findById(Long id) { return em.find(Member.class, id); }

    public Optional<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m " +
                "where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<Member> findByUsername(String username) {
        return em.createQuery("select m from Member m " +
                        "where m.email = :username", Member.class)
                .setParameter("username", username)
                .getResultList().stream()
                .findFirst();
    }

    public boolean existsByName(String username) {
        return em.createQuery("select count(m)>0 from Member m " +
                "where m.username = :name", Boolean.class)
                .setParameter("name", username)
                .getSingleResult();
    }

    public boolean existsByEmail(String email) {
        return em.createQuery("select count(m)>0 from Member m " +
                        "where m.email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
