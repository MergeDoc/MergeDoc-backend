package com.mergedoc.backend.dir.repository;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.InDIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.entity.UnitInDIR;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

    public List<DIR> findDIRsInDIR(Long dirId) {
        return em.createQuery("select d from DIR d " +
                "where d.dir.id = :id" ,DIR.class)
                .setParameter("id", dirId)
                .getResultList();
    }
    public List<PageInDIR> findPagesInDIR(Long dirId) {
        return em.createQuery("select p from PageInDIR p " +
                        "where p.dir.id = :id" ,PageInDIR.class)
                .setParameter("id", dirId)
                .getResultList();
    }

    public List<UnitInDIR> findUnitsInDIR(Long dirId) {
        return em.createQuery("select u from UnitInDIR u " +
                        "where u.dir.id = :id" ,UnitInDIR.class)
                .setParameter("id", dirId)
                .getResultList();
    }

    public boolean dirExistsInDirByName(Long dirId, String name) {
        return em.createQuery("select count(d) > 0 from DIR d " +
                "where d.dir.id = :dirId and d.name = :name", Boolean.class)
                .setParameter("dirId", dirId)
                .setParameter("name", name)
                .getSingleResult();
    }

    public boolean pageExistsInDirByName(Long dirId, String name) {
        return em.createQuery("select count(p) > 0 from PageInDIR p " +
                        "where p.dir.id = :dirId and p.name = :name", Boolean.class)
                .setParameter("dirId", dirId)
                .setParameter("name", name)
                .getSingleResult();
    }





    public List<InDIR> findMemberFilesByPath(Long memberId, String path) {
        List<InDIR> result = new ArrayList<>();

        result.addAll(findAllMemberDIRsByPath(memberId, path));
        result.addAll(findMemberPagesByPath(memberId, path));
        result.addAll(findMemberUnitsByPath(memberId, path));

        return result;
    }

    public List<DIR> findAllMemberDIRsByPath(Long memberId, String path) {
        return em.createQuery("select d from DIR  d " +
                "where d.member.id = :memberId and d.path = :path", DIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public List<PageInDIR> findMemberPagesByPath(Long memberId, String path) {
        return em.createQuery("select p from PageInDIR  p " +
                        "where p.member.id = :memberId and p.path = :path", PageInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public List<UnitInDIR> findMemberUnitsByPath(Long memberId, String path) {
        return em.createQuery("select u from UnitInDIR  u " +
                        "where u.member.id = :memberId and u.path = :path", UnitInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .getResultList();
    }

    public Optional<DIR> findMemberDIRByPathAndName(Long memberId, String path, String name) {
        return em.createQuery("select d from DIR  d " +
                        "where d.member.id = :memberId and d.path = :path and d.name = :name", DIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<PageInDIR> findMemberPageByPathAndName(Long memberId, String path, String name) {
        return em.createQuery("select p from PageInDIR  p " +
                        "where p.member.id = :memberId and p.path = :path and p.name = :name", PageInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    public Optional<UnitInDIR> findMemberUnitByPathAndName(Long memberId, String path, String name) {
        return em.createQuery("select u from UnitInDIR  u " +
                        "where u.member.id = :memberId and u.path = :path and u.name = :name", UnitInDIR.class)
                .setParameter("memberId", memberId)
                .setParameter("path", path)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }
}
