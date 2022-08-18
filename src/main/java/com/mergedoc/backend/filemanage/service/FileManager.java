package com.mergedoc.backend.filemanage.service;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.InDIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.repository.DIRRepository;
import com.mergedoc.backend.filemanage.dto.CreateDIRForm;
import com.mergedoc.backend.filemanage.dto.CreatePageForm;
import com.mergedoc.backend.filemanage.dto.FindFilesInDIRForm;
import com.mergedoc.backend.filemanage.dto.FindPageForm;
import com.mergedoc.backend.member.entity.Member;
import com.mergedoc.backend.member.repository.MemberRepository;
import com.mergedoc.backend.page.entity.Page;
import com.mergedoc.backend.page.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileManager {

    private final String ROOT_PATH = "/";
    private final String ROOT_NAME = "root";

    private final PageRepository pageRepository;
    private final DIRRepository dirRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createRootDIR(Long memberId) {
        Member findMember = memberRepository.findById(memberId);

        DIR dir = DIR.builder()
                .path(ROOT_PATH)
                .name(ROOT_NAME)
                .member(findMember)
                .build();

        dirRepository.saveDIR(dir);
        return dir.getId();
    }

    // dir 내부에 있는 file 을 찾는데 찾으려는 파일들의 생성 유저가 요청 유저 정보랑 다르면 예외
    public List<DIR> findDIRsInDIR(FindFilesInDIRForm form) {
        List<DIR> dirs = dirRepository.findDIRsInDIR(form.getDirId());

        dirs.stream().findFirst().ifPresent(d -> validateDirOwner(form.getMemberId(), d));

        return dirs;
    }
    public List<PageInDIR> findPagesInDIR(FindFilesInDIRForm form) {
        List<PageInDIR> pages = dirRepository.findPagesInDIR(form.getDirId());

        pages.stream().findFirst().ifPresent(p -> validatePageOwner(form.getMemberId(), p));

        return pages;
    }

    /* Member 가입 시 생성되는 Root DIR*/

    public List<InDIR> findAllFiles(FindFilesInDIRForm form) {
        List<InDIR> result = new ArrayList<>();
        result.addAll(findDIRsInDIR(form));
        result.addAll(findPagesInDIR(form));

        return result;
//        각 파일들에 대해서 type 을 포함해서 데이터로 돌려줄 수 있어야함
    }

    @Transactional
    public Long createDIR(CreateDIRForm form) {
        DIR parentDir = dirRepository.findDIRById(form.getDirId());

//        생성하려는 dir 의 소유자가 요청 유저와 일치해야한다
        validateDirOwner(form.getMemberId(), parentDir);
        validateDirDuplicated(form.getDirId(), form.getName());

//        member id 만 저장하면 되는데 select 한 개가 더 나간다,, 이 부분은 개선이 필요한듯, JpaRepository getOne 혹은 직접 쿼리문 작성
        Member member = memberRepository.findById(form.getMemberId());

        DIR dir = DIR.builder()
                .parent(parentDir)
                .path(producePathFromParentDIR(parentDir.getPath(), parentDir.getName()))
                .name(form.getName())
                .member(member)
                .build();

        dirRepository.saveDIR(dir);

        return dir.getId();
    }

    @Transactional
    public Long createPage(CreatePageForm form) {
        DIR dir = dirRepository.findDIRById(form.getDirId());

        validateDirOwner(form.getMemberId(), dir);
        validatePageDuplicated(dir.getId(), form.getName());

        Member member = memberRepository.findById(form.getMemberId());

        Page page = Page.builder()
                .name(form.getName())
                .build();
        pageRepository.savePage(page);

        PageInDIR pageInDIR = PageInDIR.builder()
                .page(page)
                .dir(dir)
                .member(member)
                .path(producePathFromParentDIR(dir.getPath(), dir.getName()))
                .name(form.getName())
                .build();
        dirRepository.savePageInDIR(pageInDIR);

        return pageInDIR.getId();
    }

    public Page findPage(FindPageForm form) {
        PageInDIR findPage = dirRepository.findPageById(form.getPageId());

        validatePageOwner(form.getMemberId(), findPage);

        return pageRepository.findPageById(findPage.getPage().getId());
    }



    private String producePathFromParentDIR(String path, String name) {
        return path + name + "/";
    }

    private void validateDirOwner(Long memberId, DIR dir) {
        if(!dir.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("올바르지 않은 접근입니다.");
        }
    }

    private void validatePageOwner(Long memberId, PageInDIR page) {
        if(!page.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("올바르지 않은 접근입니다.");
        }
    }

    private void validateDirDuplicated(Long dirId, String name) {
        if (dirRepository.dirExistsInDirByName(dirId, name)) {
            throw new IllegalStateException("중복된 디렉토리가 존재합니다.");
        }
    }
    private void validatePageDuplicated(Long dirId, String name) {
        if (dirRepository.pageExistsInDirByName(dirId, name)) {
            throw new IllegalStateException("중복된 페이지가 존재합니다.");
        }
    }
}
