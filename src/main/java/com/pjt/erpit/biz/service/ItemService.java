package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.item.CheckDuplicationDTO;
import com.pjt.erpit.biz.dto.item.CreateItemDTO;
import com.pjt.erpit.biz.dto.item.CreateItemPriceDTO;
import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.ItemPrice;
import com.pjt.erpit.biz.entity.history.ItemHistory;
import com.pjt.erpit.biz.entity.history.convert.ItemConvert;
import com.pjt.erpit.biz.repository.BuyerRepository;
import com.pjt.erpit.biz.repository.ItemHistoryRepository;
import com.pjt.erpit.biz.repository.ItemPriceRepository;
import com.pjt.erpit.biz.repository.ItemRepository;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 판매부번 관련 Service
 */
@SuppressWarnings({"CallToPrintStackTrace", "SpellCheckingInspection", "ExtractMethodRecommender"})
@Slf4j
@Service
public class ItemService {
    private final BuyerRepository buyerRepository;
    private final ItemRepository itemRepository;
    private final ItemHistoryRepository itemHistoryRepository;
    private final ItemPriceRepository itemPriceRepository;
    private final ItemConvert itemConvert;

    public ItemService(BuyerRepository buyerRepository, ItemRepository itemRepository, ItemHistoryRepository itemHistoryRepository, ItemPriceRepository itemPriceRepository, ItemConvert itemConvert) {
        this.buyerRepository = buyerRepository;
        this.itemRepository = itemRepository;
        this.itemHistoryRepository = itemHistoryRepository;
        this.itemPriceRepository = itemPriceRepository;
        this.itemConvert = itemConvert;
    }

    /**
     * 판매부번코드 중복 검사
     *
     * @param itemcd p1
     * @return CheckDuplicationDTO.Response
     */
    public CheckDuplicationDTO.Response checkDuplication(String itemcd) {
        CheckDuplicationDTO.Response response = new CheckDuplicationDTO.Response();
        response.setIsDuplication(itemRepository.existsByItemcd(itemcd));
        return response;
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

    /**
     * 판매가격 생성
     *
     * @param request                p1
     * @param createItemPriceDTOList p2
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> createItemPrice(HttpServletRequest request, List<CreateItemPriceDTO.Request> createItemPriceDTOList) {
        if (createItemPriceDTOList == null || createItemPriceDTOList.isEmpty()) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "empty item price list");
        }
        if (!buyerRepository.existsByBuyercd(createItemPriceDTOList.get(0).getBuyercd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "buyer is not exist");
        }
        if (!itemRepository.existsByItemcd(createItemPriceDTOList.get(0).getItemcd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "item is not exist");
        }

        String ip = request.getRemoteAddr();
        List<ItemPrice> itemPriceList = new ArrayList<>();

        for (CreateItemPriceDTO.Request createItemPriceDTO : createItemPriceDTOList) {
            ItemPrice itemPrice = new ItemPrice();
            itemPrice.setBuyercd(createItemPriceDTO.getBuyercd());
            itemPrice.setItemcd(createItemPriceDTO.getItemcd());
            itemPrice.setBuyersupplyprice(createItemPriceDTO.getBuyersupplyprice());
            itemPrice.setSurtax(createItemPriceDTO.getSurtax());
            itemPrice.setSalesprice(createItemPriceDTO.getSalesprice());
            itemPrice.setAddipaddr(ip);
            itemPrice.setUpdipaddr(ip);
            itemPriceList.add(itemPrice);
        }

        try {
            itemPriceRepository.saveAll(itemPriceList);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }
}
