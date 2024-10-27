package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.item.CreateItemDTO;
import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.history.ItemHistory;
import com.pjt.erpit.biz.entity.history.convert.ItemConvert;
import com.pjt.erpit.biz.repository.ItemHistoryRepository;
import com.pjt.erpit.biz.repository.ItemRepository;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 판매부번 관련 Service
 */
@SuppressWarnings({"CallToPrintStackTrace"})
@Slf4j
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemHistoryRepository itemHistoryRepository;
    private final ItemConvert itemConvert;

    public ItemService(ItemRepository itemRepository, ItemHistoryRepository itemHistoryRepository, ItemConvert itemConvert) {
        this.itemRepository = itemRepository;
        this.itemHistoryRepository = itemHistoryRepository;
        this.itemConvert = itemConvert;
    }

    /**
     * 판매부번 생성
     *
     * @param request       p1
     * @param createItemDTO p2
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> createItem(HttpServletRequest request, CreateItemDTO.Request createItemDTO) {
        if (itemRepository.existsByItemcd(createItemDTO.getItemcd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "is already exist");
        }

        String ip = request.getRemoteAddr();
        Item item = new Item();
        item.setItemcd(createItemDTO.getItemcd());
        item.setItemnm(createItemDTO.getItemnm());
        item.setOriginprice(createItemDTO.getOriginprice());
        item.setSupplyprice(createItemDTO.getSupplyprice());
        item.setStock(createItemDTO.getStock());
        item.setUnit(createItemDTO.getUnit());
        item.setAddipaddr(ip);
        item.setUpdipaddr(ip);

        try {
            Item savedItem = itemRepository.save(item);
            ItemHistory itemHistory = itemConvert.toItemHistory(savedItem);
            itemHistoryRepository.save(itemHistory);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }
}
