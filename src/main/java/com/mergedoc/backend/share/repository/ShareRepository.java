package com.mergedoc.backend.share.repository;

import com.mergedoc.backend.share.entity.SharedPage;
import com.mergedoc.backend.share.util.URLGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ShareRepository {

    private final EntityManager em;

    public void saveSharedPage(SharedPage sharedPage) {em.persist(sharedPage);}

    public Optional<SharedPage> findSharedPageByURL(String findURL) {
        return em.createQuery("select s from SharedPage s " +
                "where s.url = :url", SharedPage.class)
                .setParameter("url", findURL)
                .getResultList()
                .stream().findFirst();
    }

    public void deleteSharedPage(String deleteURL) {
        em.createQuery("delete from SharedPage p " +
                "where p.url = :url")
                .setParameter("url", deleteURL)
                .executeUpdate();
    }

    public void deleteExpiredSharedPage() {
        em.createQuery("delete from SharedPage p " +
                "where p.expiredDate < :dateTime")
                .setParameter("dateTime", LocalDateTime.now())
                .executeUpdate();
    }
}
