package com.mergedoc.backend.repository;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.entity.UnitInDIR;
import com.mergedoc.backend.dir.repository.DIRRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class DIRTest {

    @Autowired
    private DIRRepository dirRepository;

    DIR dir;

    String rootPath = "/";
    String rootName = "root";

    @BeforeEach
    public void createRoot() {
        DIR rootDIR = DIR.builder().build();

        rootDIR.setName(rootName);
        rootDIR.setPath(rootPath);

        dirRepository.saveDIR(rootDIR);

        String createName = "testDIR";

        DIR parentDIR = dirRepository.findDIRByPathAndName(rootPath, rootName);

        DIR createdDIR = DIR.builder().parent(parentDIR).build();
        createdDIR.setPath(rootPath+rootName+"/");
        createdDIR.setName(createName);

        dirRepository.saveDIR(createdDIR);

        this.dir = createdDIR;

    }

    @Test
    public void findUnit() {
        DIR parentDIR = dirRepository.findDIRByPathAndName(rootPath, rootName);

        String childPath = rootPath+rootName+"/";

        UnitInDIR createUnitInDIR = UnitInDIR.builder()
                .dir(parentDIR)
                .build();

        createUnitInDIR.setPath(childPath);
        createUnitInDIR.setName("testUnit");
//        Unit 연관관계 추가 필요

        dirRepository.saveUnitInDIR(createUnitInDIR);

        UnitInDIR findUnit = dirRepository.findUnitByPathAndName(childPath, "testUnit");

        assertThat(findUnit).isEqualTo(createUnitInDIR);
    }

    @Test
    public void findPage() {
        DIR parentDIR = dirRepository.findDIRByPathAndName(rootPath, rootName);

        String childPath = rootPath+rootName+"/";

        PageInDIR createPageInDIR = PageInDIR.builder()
                .dir(parentDIR)
                .build();

        createPageInDIR.setPath(childPath);
        createPageInDIR.setName("testPage");
//        Unit 연관관계 추가 필요

        dirRepository.savePageInDIR(createPageInDIR);

        PageInDIR findUnit = dirRepository.findPageByPathAndName(childPath, "testPage");

        assertThat(findUnit).isEqualTo(createPageInDIR);
    }

    @Test
    public void findDIR() {
        DIR findDIR = dirRepository.findDIRByPathAndName("/root/", "testDIR");

        assertThat(findDIR).isEqualTo(dir);
    }


}
