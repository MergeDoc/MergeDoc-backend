package com.mergedoc.backend.filemanage.service;

import com.mergedoc.backend.dir.entity.DIR;
import com.mergedoc.backend.dir.entity.InDIR;
import com.mergedoc.backend.dir.entity.PageInDIR;
import com.mergedoc.backend.dir.repository.DIRRepository;
import com.mergedoc.backend.exceptions.NotFoundException;
import com.mergedoc.backend.filemanage.dto.CreateDIRDTO;
import com.mergedoc.backend.filemanage.dto.CreatePageDTO;
import com.mergedoc.backend.filemanage.dto.FilePathNameDTO;
import com.mergedoc.backend.page.entity.Page;
import com.mergedoc.backend.page.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileManager {

    private final String ROOT_PATH = "/";
    private final String ROOT_NAME = "root";

    private final PageRepository pageRepository;
    private final DIRRepository dirRepository;

    private DIR getDIR(String dirPath, String name) {
        return dirRepository
                .findDIRByPathAndName(dirPath, name)
                .orElseThrow(() -> new NotFoundException("디렉토리를 찾을 수 없습니다."));
    }

    /* Member 가입 시 생성되는 Root DIR*/
    @Transactional
    public void createRootDIR() {
        DIR dir = DIR.builder().build();

        dir.setPath(ROOT_PATH);
        dir.setName(ROOT_NAME);

//        member 연관관계 추가

        dirRepository.saveDIR(dir);
    }

    public void findAllFiles(FilePathNameDTO filePathNameDTO) {
        List<InDIR> allByPath = dirRepository.findAllByPath(filePathNameDTO.getPath());
        
//        각 파일들에 대해서 type 을 포함해서 데이터로 돌려줄 수 있어야함
    }

    public Long findDIR(FilePathNameDTO filePathNameDTO) {
        DIR dir = getDIR(filePathNameDTO.getPath(), filePathNameDTO.getName());

        return dir.getId();
    }

    @Transactional
    public void createDIR(CreateDIRDTO createDIRDTO) {
        DIR findDir = getDIR(createDIRDTO.getDirPath(), createDIRDTO.getName());

        DIR dir = DIR.builder()
                .parent(findDir)
                .build();

        dir.setPath(createDIRDTO.getPath());
        dir.setName(createDIRDTO.getName());

//        member 연관관계 추가

        dirRepository.saveDIR(dir);
    }



    public Long findPage(FilePathNameDTO filePathNameDTO) {
        PageInDIR pageInDIR = dirRepository.findPageByPathAndName(filePathNameDTO.getPath(), filePathNameDTO.getName())
                .orElseThrow(() -> new NotFoundException("페이지를 찾을 수 없습니다."));

        Long pageId = pageInDIR.getPage().getId();

//        page 내에서 가져올 정보가 있다면 해당 로직까지 진행
//        현재는 PageInDIR 객체에 있는 정보로도 충분

        Page findPage = pageRepository.findPageById(pageId);

        return findPage.getId();
    }

    public void createPage(CreatePageDTO createPageDTO) {
        DIR findDir = getDIR(createPageDTO.getDirPath(), createPageDTO.getDirName());

        Page page = Page.builder()
                .name(createPageDTO.getName())
                .build();

        pageRepository.savePage(page);

        PageInDIR pageInDIR = PageInDIR.builder()
                .dir(findDir)
                .page(page)
                .build();

        pageInDIR.setPath(createPageDTO.getPath());
        pageInDIR.setName(createPageDTO.getName());

        dirRepository.savePageInDIR(pageInDIR);
    }


}
