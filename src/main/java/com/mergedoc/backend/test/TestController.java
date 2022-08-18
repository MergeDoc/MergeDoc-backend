package com.mergedoc.backend.test;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/logtest")
    public void errorTest(@RequestParam @Nullable boolean error) throws Exception{
        if (error) {
            throw new Exception("류형욱");
        }
    }
}
