package com.example.bot.biz.controller;

import com.example.bot.core.config.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

    @PostMapping
    public ResponseResult<?> createBuyer() {
        return null;
    }
}
