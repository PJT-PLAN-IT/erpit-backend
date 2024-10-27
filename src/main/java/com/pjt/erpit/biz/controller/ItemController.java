package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.item.CreateItemDTO;
import com.pjt.erpit.biz.service.ItemService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 판매부번 관련 Controller
 * auth: admin
 */
@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 판매부번 생성
     *
     * @param request       p1
     * @param createItemDTO p2
     * @return ResponseResult<?>
     */
    @PostMapping
    public ResponseResult<?> createItem(HttpServletRequest request, @Valid @RequestBody CreateItemDTO.Request createItemDTO) {
        return itemService.createItem(request, createItemDTO);
    }
}
