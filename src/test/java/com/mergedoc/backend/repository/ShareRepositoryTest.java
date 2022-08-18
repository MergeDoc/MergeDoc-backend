package com.mergedoc.backend.repository;

import com.mergedoc.backend.exception.NotFoundException;
import com.mergedoc.backend.share.entity.SharedPage;
import com.mergedoc.backend.share.repository.ShareRepository;
import com.mergedoc.backend.share.util.URLGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ShareRepositoryTest {

    @Autowired
    private ShareRepository shareRepository;

//    @Autowired
//    private DIRRepository dirRepository;

    @Autowired
    private URLGenerator urlGenerator;

    @Test
    public void findSharePage() {
        String url = urlGenerator.generateURLWithNumeric(20);

//        Page page = dirRepository.findPageByPathAndName("/root/", "test").getPage();

        SharedPage sharedPage = SharedPage.builder()
                .expiredDate(LocalDateTime.now().plusDays(7))
                .url(url)
                .build();

        shareRepository.saveSharedPage(sharedPage);

        SharedPage findPage = shareRepository.findSharedPageByURL(url)
                .orElseThrow(() -> new NotFoundException("공유 페이지를 찾을 수 없습니다."));

        assertThat(findPage).isEqualTo(sharedPage);
    }

    @Test
    public void deleteExpiredSharePage() {
        String url = urlGenerator.generateURLWithNumeric(20);

        SharedPage sharedPage = SharedPage.builder()
                .expiredDate(LocalDateTime.now().minusDays(7))
                .url(url)
                .build();

        shareRepository.saveSharedPage(sharedPage);

        shareRepository.deleteExpiredSharedPage();

        try {
            shareRepository.findSharedPageByURL(url);
            fail("삭제 실패");
        } catch (Exception e) {
        }
    }
}
