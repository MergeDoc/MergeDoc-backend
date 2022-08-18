package com.mergedoc.backend.repository;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.entity.UnitInDIR;
import com.mergedoc.backend.dir.repository.DIRRepository;
import com.mergedoc.backend.exception.NotFoundException;
import com.mergedoc.backend.member.entity.Member;
import com.mergedoc.backend.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class DIRTest {

    @Autowired
    private DIRRepository dirRepository;
    @Autowired
    private MemberRepository memberRepository;

    DIR dir;
    Member member;

    String rootPath = "/";
    String rootName = "root";

    @BeforeEach
    public void createRoot() {
        member = Member.builder().build();
        memberRepository.save(member);

        DIR rootDIR = DIR.builder()
                .member(member)
                .path(rootPath)
                .name(rootName)
                .build();



        dirRepository.saveDIR(rootDIR);

        String createName = "testDIR";

        DIR parentDIR = dirRepository.findMemberDIRByPathAndName(member.getId(), rootPath, rootName)
                .orElseThrow(() -> new NotFoundException("디렉토리를 찾을 수 없습니다."));

        DIR createdDIR = DIR.builder()
                .parent(parentDIR)
                .path(rootPath+rootName+"/")
                .name(createName)
                .build();

        dirRepository.saveDIR(createdDIR);

        this.dir = createdDIR;

    }

    @Test
    public void findUnit() {
        DIR parentDIR = dirRepository.findMemberDIRByPathAndName(member.getId(), rootPath, rootName)
                .orElseThrow(() -> new NotFoundException("디렉토리를 찾을 수 없습니다."));

        String childPath = rootPath+rootName+"/";

        UnitInDIR createUnitInDIR = UnitInDIR.builder()
                .dir(parentDIR)
                .member(member)
                .path(childPath)
                .name("testUnit")
                .build();

//        Unit 연관관계 추가 필요

        dirRepository.saveUnitInDIR(createUnitInDIR);

        UnitInDIR findUnit = dirRepository.findMemberUnitByPathAndName(member.getId(), childPath, "testUnit")
                .orElseThrow(() -> new NotFoundException("유닛을 찾을 수 없습니다."));

        assertThat(findUnit).isEqualTo(createUnitInDIR);
    }

    @Test
    public void findPage() {
        DIR parentDIR = dirRepository.findMemberDIRByPathAndName(member.getId(), rootPath, rootName)
                .orElseThrow(() -> new NotFoundException("디렉토리를 찾을 수 없습니다."));

        String childPath = rootPath+rootName+"/";

        PageInDIR createPageInDIR = PageInDIR.builder()
                .dir(parentDIR)
                .member(member)
                .path(childPath)
                .name("testPage")
                .build();

//        Unit 연관관계 추가 필요

        dirRepository.savePageInDIR(createPageInDIR);

        PageInDIR findUnit = dirRepository.findMemberPageByPathAndName(member.getId(), childPath, "testPage")
                .orElseThrow(() -> new NotFoundException("페이지를 찾을 수 없습니다."));

        assertThat(findUnit).isEqualTo(createPageInDIR);
    }

    @Test
    public void findRootFiles() {
        DIR findDIR = dirRepository.findMemberDIRByPathAndName(member.getId(), rootPath, rootName)
                .orElseThrow(() -> new NotFoundException("디렉토리를 찾을 수 없습니다."));

        String childPath = rootPath+rootName+"/";

        IntStream.rangeClosed(1, 5).forEach(i -> {
            PageInDIR createPage = PageInDIR.builder()
                    .member(member)
                    .path("/root/")
                    .name("page" + i)
                    .dir(findDIR)
                    .build();

            dirRepository.savePageInDIR(createPage);
        });

        IntStream.rangeClosed(1, 5).forEach(i -> {
            UnitInDIR createUnit = UnitInDIR.builder()
                    .member(member)
                    .path("/root/")
                    .name("unit" + i)
                    .dir(findDIR)
                    .build();

            dirRepository.saveUnitInDIR(createUnit);
        });

        List<PageInDIR> findPages = dirRepository.findMemberPagesByPath(member.getId(), childPath);
        List<UnitInDIR> findUnits = dirRepository.findMemberUnitsByPath(member.getId(), childPath);

        for (PageInDIR inDIR : findPages) {
            System.out.println(inDIR.getName());
        }

        for (UnitInDIR inDIR : findUnits) {
            System.out.println(inDIR.getName());
        }

    }
}
