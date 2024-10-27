package com.pjt.erpit.test;

import com.pjt.erpit.core.config.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/")
    public ResponseResult<?> test() {
        return ResponseResult.ofSuccess("success", null);
    }
}
