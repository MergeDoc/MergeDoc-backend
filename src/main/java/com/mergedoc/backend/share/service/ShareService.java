package com.mergedoc.backend.share.service;

import com.mergedoc.backend.exception.NotFoundException;
import com.mergedoc.backend.filemanage.dto.FindPageForm;
import com.mergedoc.backend.filemanage.service.FileManager;
import com.mergedoc.backend.page.entity.Page;
import com.mergedoc.backend.page.repository.PageRepository;
import com.mergedoc.backend.share.dto.CreateSharedPageForm;
import com.mergedoc.backend.share.entity.SharedPage;
import com.mergedoc.backend.share.repository.ShareRepository;
import com.mergedoc.backend.share.util.URLGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShareService {


    private final ShareRepository shareRepository;
    private final URLGenerator urlGenerator;
    private final PageRepository pageRepository;

    private final FileManager fileManager;

    public String createSharePage(CreateSharedPageForm form) {
        FindPageForm findPageForm = new FindPageForm(form.getPageId(), form.getMemberId());

        Page findPage = fileManager.findPage(findPageForm);

        SharedPage sharedPage = SharedPage.builder()
                .page(findPage)
                .url(urlGenerator.generateURLWithNumeric(30))
                .expiredDate(LocalDateTime.now().plusDays(7))
                .build();

        shareRepository.saveSharedPage(sharedPage);

        return sharedPage.getUrl();
    }

//    url 이 있는 사용자는 바로 Page 접근 가능
    public Page findSharedPage(String url) {
        SharedPage findSharedPage = shareRepository.findSharedPageByURL(url)
                .orElseThrow(() -> new NotFoundException("공유 페이지를 찾을 수 없습니다."));

        return pageRepository.findPageById(findSharedPage.getPage().getId());
    }
}
