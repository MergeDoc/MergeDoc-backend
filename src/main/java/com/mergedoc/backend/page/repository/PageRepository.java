package com.mergedoc.backend.page.repository;

import com.mergedoc.backend.page.entity.Page;
import com.mergedoc.backend.page.entity.PageUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PageRepository {

    private final EntityManager em;

    public void savePage(Page page) {em.persist(page);}

    public void savePageUnit(PageUnit pageUnit) {em.persist(pageUnit);}

    public void deletePage(Page page) {em.remove(page);}

    public void deletePageUnit(PageUnit pageUnit) {em.remove(pageUnit);}

    public void changePageName(Page page, String name) {
        page.changeName(name);
    }

    public Page findPageById(Long id) {return em.find(Page.class, id);}

    public List<PageUnit> findPageUnitsByPage(Page page) {
        return em.createQuery("select u from PageUnit u " +
                "where u.page = :page", PageUnit.class)
                .setParameter("page", page)
                .getResultList();
    }
}
