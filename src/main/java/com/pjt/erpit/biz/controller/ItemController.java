package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.item.*;
import com.pjt.erpit.biz.service.ItemService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 판매부번 관련 Controller
 * auth: admin
 */
@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 판매부번코드 중복 검사
     *
     * @param itemcd p1
     * @return ResponseResult<?>
     */
    @GetMapping("/check")
    public ResponseResult<?> checkDuplication(@RequestParam String itemcd) {
        return ResponseResult.ofSuccess("success", itemService.checkDuplication(itemcd));
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

    /**
     * 판매가격 생성
     *
     * @param request                p1
     * @param createItemPriceDTOList p2
     * @return ResponseResult<?>
     */
    @PostMapping("/price")
    public ResponseResult<?> createItemPrice(HttpServletRequest request, @Valid @RequestBody List<CreateItemPriceDTO.Request> createItemPriceDTOList) {
        return itemService.createItemPrice(request, createItemPriceDTOList);
    }

    /**
     * 판매부번 조회
     * @param item
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<?> itemList(@RequestParam(required = false) String item) {
        List<ItemListDTO> result = itemService.itemList(item);
        return ResponseResult.ofSuccess("success", result);
    }

    /**
     * 판매부번 수정
     * @param updateItemDTO
     * @return
     */
    @PutMapping
    public ResponseResult<?> updateItem(HttpServletRequest request,@RequestBody UpdateItemDTO updateItemDTO) {
        return itemService.updateItem(request, updateItemDTO);
    }

    /**
     * 판배부번 비활성화 처리
     * @param deactivateItemDTO
     * @return
     */
    @PutMapping("/deactivate")
    public ResponseResult<?> deactivateItem(@RequestBody DeactivateItemDTO deactivateItemDTO) {
        return itemService.deactivateItem(deactivateItemDTO);
    }

    /**
     * 판매가격 조회
     * @param item
     * @param buyer
     * @return
     */
    @GetMapping("/price/list")
    public ResponseResult<?> itemPriceList(@RequestParam(required = false) String item, @RequestParam(required = false) String buyer) {
        List<ItemPriceListDTO> result = itemService.itemPriceList(item, buyer);
        return ResponseResult.ofSuccess("success", result);
    }

    /**
     * 판매가격 비활성화 처리
     * @param deactivateItemPriceDTO
     * @return
     */
    @PutMapping("/price/deactivate")
    public ResponseResult<?> deactivateItemPrice(@RequestBody DeactivateItemPriceDTO deactivateItemPriceDTO) {
        return itemService.deactivateItemPrice(deactivateItemPriceDTO);
    }
}
