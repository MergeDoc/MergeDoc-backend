package com.mergedoc.backend.dir.repository;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.InDIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.entity.UnitInDIR;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DIRRepository {

    private final EntityManager em;

    public void saveDIR(DIR dir) {em.persist(dir);}
    public void saveUnitInDIR(UnitInDIR unitInDIR) {em.persist(unitInDIR);}
    public void savePageInDIR(PageInDIR pageInDir) {
        em.persist(pageInDir);
    }

    public DIR findDIRById(Long id) { return em.find(DIR.class, id); }
    public PageInDIR findPageById(Long id) { return em.find(PageInDIR.class, id); }
    public UnitInDIR findUnitById(Long id) { return em.find(UnitInDIR.class, id); }

    public List<DIR> findDIRsInDIR(Long id) {
        return em.createQuery("select d from DIR d " +
                "where d.parent = :id" ,DIR.class)
                .setParameter("id", id)
                .getResultList();
    }
    public List<PageInDIR> findPagesInDIR(Long id) {
        return em.createQuery("select p from PageInDIR p " +
                        "where p.dir.id = :id" ,PageInDIR.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<UnitInDIR> findUnitsInDIR(Long id) {
        return em.createQuery("select u from UnitInDIR u " +
                        "where u.dir.id = :id" ,UnitInDIR.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<InDIR> findAllMemberFileByPath(Long memberId, String path) {
        List<InDIR> result = new ArrayList<>();

        result.addAll(findAllMemberDIRsByPath(memberId, path));
        result.addAll(findAllMemberPagesByPath(memberId, path));
        result.addAll(findAllMemberUnitsByPath(memberId, path));

        return result;
    }

    public List<DIR> findAllMemberDIRsByPath(Long memberId, String path) {
        return em.createQuery("select d from DIR  d " +
                "where d.member.id = :memberId and d.path = :path", DIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public List<PageInDIR> findAllMemberPagesByPath(Long memberId, String path) {
        return em.createQuery("select p from PageInDIR  p " +
                        "where p.member.id = :memberId and p.path = :path", PageInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public List<UnitInDIR> findAllMemberUnitsByPath(Long memberId, String path) {
        return em.createQuery("select u from UnitInDIR  u " +
                        "where u.member.id = :memberId and u.path = :path", UnitInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public Optional<DIR> findMemberDIRByPathName(Long memberId, String path, String name) {
        return em.createQuery("select d from DIR  d " +
                        "where d.member.id = :memberId and d.path = :path and d.name = :name", DIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<PageInDIR> findMemberPageByPathName(Long memberId, String path, String name) {
        return em.createQuery("select p from PageInDIR  p " +
                        "where p.member.id = :memberId and p.path = :path and p.name = :name", PageInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<UnitInDIR> findMemberUnitByPathName(Long memberId, String path, String name) {
        return em.createQuery("select u from UnitInDIR  u " +
                        "where u.member.id = :memberId and u.path = :path and u.name = :name", UnitInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }
}
