package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.buyer.CreateBuyerDTO;
import com.pjt.erpit.biz.service.BuyerService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 바이어 관련 Controller
 * auth: admin
 */
@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    /**
     * 바이어 생성
     *
     * @param createBuyerDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping
    public ResponseResult<?> createBuyer(@Valid @RequestBody CreateBuyerDTO.Request createBuyerDTO) {
        return null;
    }
}
