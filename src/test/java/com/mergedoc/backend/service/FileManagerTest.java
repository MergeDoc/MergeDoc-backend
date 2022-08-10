package com.mergedoc.backend.service;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.filemanage.dto.CreateDIRForm;
import com.mergedoc.backend.filemanage.dto.CreatePageForm;
import com.mergedoc.backend.filemanage.dto.FindFilesInDIRForm;
import com.mergedoc.backend.filemanage.dto.FindPageForm;
import com.mergedoc.backend.filemanage.service.FileManager;
import com.mergedoc.backend.member.entity.Member;
import com.mergedoc.backend.member.repository.MemberRepository;
import com.mergedoc.backend.page.entity.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class FileManagerTest {

    @Autowired private FileManager fileManager;
    @Autowired private MemberRepository memberRepository;


    private final String ROOT_PATH = "/";
    private final String ROOT_NAME = "root";

    Member member;
    Long rootId;

    @BeforeEach
    public void createRootDIR() {
        member = Member.builder().build();
        memberRepository.save(member);

        rootId = fileManager.createRootDIR(member.getId());
    }

    @Test
    public void createDIR() {
        CreateDIRForm form = new CreateDIRForm(rootId, "sampleDIR", member.getId());
        fileManager.createDIR(form);

        FindFilesInDIRForm findForm = new FindFilesInDIRForm(rootId, null, member.getId());
        List<DIR> findDIRs = fileManager.findDIRsInDIR(findForm);

        for (DIR dir : findDIRs) {
            System.out.println(dir.getName());
        }
    }

    @Test
    public void createPage() {
        CreatePageForm form = new CreatePageForm(rootId, "samplePage", member.getId());
        fileManager.createPage(form);
        FindFilesInDIRForm findForm = new FindFilesInDIRForm(rootId, null, member.getId());
        List<PageInDIR> findPages = fileManager.findPagesInDIR(findForm);

        for (PageInDIR page : findPages) {
            System.out.println(page.getName());
        }
    }

    @Test
    public void findPage() {
        CreatePageForm form = new CreatePageForm(rootId, "samplePage", member.getId());
        Long pageId = fileManager.createPage(form);

        FindPageForm form2 = new FindPageForm(pageId, member.getId());
        Page page = fileManager.findPage(form2);

        System.out.println(page.getName());
    }

}
