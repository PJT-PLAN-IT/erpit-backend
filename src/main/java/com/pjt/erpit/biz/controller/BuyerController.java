package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.buyer.BuyerListDTO;
import com.pjt.erpit.biz.dto.buyer.CreateBuyerDTO;
import com.pjt.erpit.biz.dto.buyer.UpdateBuyerDTO;
import com.pjt.erpit.biz.service.BuyerService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 바이어 관련 Controller
 * auth: admin
 */
@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    /**
     * 바이어코드 중복 검사
     *
     * @param buyercd p1
     * @return ResponseResult<?>
     */
    @GetMapping("/check")
    public ResponseResult<?> checkDuplication(@RequestParam String buyercd) {
        return ResponseResult.ofSuccess("success", buyerService.checkDuplication(buyercd));
    }

    /**
     * 바이어 생성
     *
     * @param createBuyerDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping
    public ResponseResult<?> createBuyer(HttpServletRequest request, @Valid @RequestBody CreateBuyerDTO.Request createBuyerDTO) {
        return buyerService.createBuyer(request, createBuyerDTO);
    }

    /**
     * 바이어 조회
     * @param buyer
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<?> buyerList(@RequestParam(required = false) String buyer) {
        List<BuyerListDTO> result = buyerService.buyerList(buyer);
        return ResponseResult.ofSuccess("success", result);
    }

    /**
     * 바이어 수정
     * @param updateBuyerDTO
     * @return
     */
    @PutMapping
    public ResponseResult<?> updateBuyer(@RequestBody UpdateBuyerDTO updateBuyerDTO) {
        return buyerService.updateBuyer(updateBuyerDTO);
    }
}
