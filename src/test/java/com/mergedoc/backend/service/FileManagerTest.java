package com.mergedoc.backend.service;

import com.mergedoc.backend.filemanage.dto.CreatePageDTO;
import com.mergedoc.backend.filemanage.dto.FilePathNameDTO;
import com.mergedoc.backend.filemanage.service.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FileManagerTest {

    @Autowired private FileManager fileManager;

    @BeforeEach
    public void createRootDIR() {
        fileManager.createRootDIR();
    }

    @Test
    public void findDIR() {
        FilePathNameDTO filePathNameDTO = new FilePathNameDTO(
                "/",
                "root"
        );
        System.out.println(fileManager.findDIR(filePathNameDTO));
    }

    @Test
    public void createPage() {
        CreatePageDTO createPageDTO = new CreatePageDTO(
                "/", "root", "sample"
        );

        fileManager.createPage(createPageDTO);

        FilePathNameDTO filePathNameDTO = new FilePathNameDTO(
                "/root/", "sample"
        );

        System.out.println(fileManager.findPage(filePathNameDTO));
    }
}
