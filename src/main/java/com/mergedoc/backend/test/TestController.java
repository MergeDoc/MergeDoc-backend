package com.mergedoc.backend.test;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Slf4j
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/logtest")
    public String errorTest(@RequestParam @Nullable boolean error) throws Exception{
        String test = org.springframework.core.SpringVersion.getVersion();
        logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");
        if (error) {
            logger.error("Exception 발생");
            throw new Exception("류형욱");
        }
        return test;
    }
}
