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

@Repository
@RequiredArgsConstructor
public class DIRRepository {

    private final EntityManager em;

    public void saveDIR(DIR dir) {
        em.persist(dir);
    }
    public void saveUnitInDIR(UnitInDIR unitInDIR) {em.persist(unitInDIR);}
    public void savePageInDIR(PageInDIR pageInDir) {
        em.persist(pageInDir);
    }

    public List<InDIR> findAllByPath(String path) {
        List<InDIR> findFiles = new ArrayList<>();

        findFiles.addAll(findDIRsByPath(path));
        findFiles.addAll(findPagesByPath(path));
        findFiles.addAll(findUnitsByPath(path));

        return findFiles;
    }

    public List<DIR> findDIRsByPath(String path) {
        return em.createQuery("select d from DIR d where d.path = :path", DIR.class)
                .setParameter("path", path)
                .getResultList();
    }

    public DIR findDIRByPathAndName(String path, String name) {
        return em.createQuery("select d from DIR d where " +
                        "d.path = :path and d.name = :name", DIR.class)
                .setParameter("path", path)
                .setParameter("name", name)
                .getSingleResult();
    }


    public List<UnitInDIR> findUnitsByPath(String path) {
        return em.createQuery("select u from UnitInDIR u where u.path = :path", UnitInDIR.class)
                .setParameter("path", path)
                .getResultList();
    }

    public UnitInDIR findUnitByPathAndName(String path, String name) {
        return em.createQuery("select u from UnitInDIR u where " +
                        "u.path = :path and u.name = :name", UnitInDIR.class)
                .setParameter("path", path)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<PageInDIR> findPagesByPath(String path) {
        return em.createQuery("select p from PageInDIR p where p.path = :path", PageInDIR.class)
                .setParameter("path", path)
                .getResultList();
    }

    public PageInDIR findPageByPathAndName(String path, String name) {
        return em.createQuery("select p from PageInDIR p where " +
                        "p.path = :path and p.name = :name", PageInDIR.class)
                .setParameter("path", path)
                .setParameter("name", name)
                .getSingleResult();
    }
}
