package com.mergedoc.backend.repository;

import com.mergedoc.backend.page.entity.Page;
import com.mergedoc.backend.page.entity.PageUnit;
import com.mergedoc.backend.page.repository.PageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PageRepositoryTest {

    @Autowired
    private PageRepository pageRepository;


    Page page;

    @BeforeEach
    public void createPage() {
        page = Page.builder()
                .name("sample_page")
                .build();

        pageRepository.savePage(page);

        //        DIR 에 추가

    }

    @Test
    public void findPage() {
        Page findPage = pageRepository.findPageById(page.getId());

        assertThat(findPage).isEqualTo(page);
    }

    @Test
    public void addPageUnit() {
//        Unit 생성

        PageUnit pageUnit = PageUnit.builder()
                .page(page)
                .build();
//        Unit 매핑

        pageRepository.savePageUnit(pageUnit);

        List<PageUnit> findPageUnit = pageRepository.findPageUnitsByPage(page);

        assertThat(findPageUnit.get(0)).isEqualTo(pageUnit);
    }
}
