package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.item.*;
import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.ItemPrice;
import com.pjt.erpit.biz.entity.Order;
import com.pjt.erpit.biz.entity.history.ItemHistory;
import com.pjt.erpit.biz.entity.history.OrderHistory;
import com.pjt.erpit.biz.entity.history.convert.ItemConvert;
import com.pjt.erpit.biz.repository.BuyerRepository;
import com.pjt.erpit.biz.repository.ItemHistoryRepository;
import com.pjt.erpit.biz.repository.ItemPriceRepository;
import com.pjt.erpit.biz.repository.ItemRepository;
import com.pjt.erpit.core.config.ResponseResult;
import com.pjt.erpit.core.util.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 판매부번 관련 Service
 */
@SuppressWarnings({"CallToPrintStackTrace", "SpellCheckingInspection", "ExtractMethodRecommender"})
@Slf4j
@Service
@Transactional(readOnly = true)
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

    /**
     * 판매부번 조회
     *
     * @param item
     * @return
     */
    public List<ItemListDTO> itemList(String item) {
        List<Item> itemList;
        if(item == null || item.isEmpty()) {
            itemList = itemRepository.findAll();
        }else{
            itemList = itemRepository.findByItemcdOrItemnm(item);
        }
        List<ItemListDTO> result = itemList.stream()
                .map(i -> {
                    ItemListDTO dto = entityToDto(i);
                    return dto;
                })
                .toList();
        return result;
    }

    /**
     * 판매부번 수정
     *
     * @param updateItemDTO
     * @return
     */
    @Transactional
    public ResponseResult<?> updateItem(HttpServletRequest request, UpdateItemDTO updateItemDTO) {
        String ip = request.getRemoteAddr();


        Item item = itemRepository.findByItemid(updateItemDTO.getItemId());
        item.setItemnm(updateItemDTO.getItemNm());
        item.setOriginprice(updateItemDTO.getOriginPrice());
        item.setSupplyprice(updateItemDTO.getSupplyPrice());
        item.setStock(updateItemDTO.getStock());
        item.setUnit(updateItemDTO.getUnit());
        item.setUseyn(updateItemDTO.getUseYn());
        item.setAddipaddr(ip);

        itemRepository.save(item);
        item = itemRepository.findByItemid(updateItemDTO.getItemId());

        ItemHistory itemHistory = itemConvert.toItemHistory(item);

        try {
            itemHistoryRepository.save(itemHistory);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }


//        Item item = updateItemDTO.toEntity();
//        itemRepository.save(item);
//
//        ItemHistory itemHistory = itemConvert.toItemHistory(item);
//        itemHistoryRepository.save(itemHistory);

        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 비활성화 처리
     *
     * @param deactivateItemDTO
     * @return
     */
    @Transactional
    public ResponseResult<?> deactivateItem(DeactivateItemDTO deactivateItemDTO) {
        Optional<Item> id = itemRepository.findById(deactivateItemDTO.getItemId());
        if (id.isPresent()) {
            Item item = Item.builder()
                    .itemid(deactivateItemDTO.getItemId())
                    .itemcd(id.get().getItemcd())
                    .itemnm(id.get().getItemnm())
                    .originprice(id.get().getOriginprice())
                    .supplyprice(id.get().getSupplyprice())
                    .stock(id.get().getStock())
                    .unit(id.get().getUnit())
                    .useyn(deactivateItemDTO.getUseYn())
                    .build();
            itemRepository.save(item);
        }
        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 판매가격 조회
     *
     * @param item
     * @param buyer
     * @return
     */
    public List<ItemPriceListDTO> itemPriceList(String item, String buyer) {
        List<ItemPrice> itemPriceList = itemPriceRepository.searchItemPrice(item, buyer);
        List<ItemPriceListDTO> result = itemPriceList.stream()
                .map(i -> {
                    SearchItemDTO searchItem = itemRepository.searchItem(i.getItemcd());
                    String buyernm = buyerRepository.searchBuyer(i.getBuyercd());
                    return entityToDto(i, searchItem, buyernm);
                })
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 판매가격 비활성화 처리
     *
     * @param deactivateItemPriceDTO
     * @return
     */
    @Transactional
    public ResponseResult<?> deactivateItemPrice(DeactivateItemPriceDTO deactivateItemPriceDTO) {
        Optional<ItemPrice> id = itemPriceRepository.findById(deactivateItemPriceDTO.getItemPriceId());
        if (id.isPresent()) {
            ItemPrice itemPrice = ItemPrice.builder()
                    .itempriceid(deactivateItemPriceDTO.getItemPriceId())
                    .buyercd(id.get().getBuyercd())
                    .itemcd(id.get().getItemcd())
                    .buyersupplyprice(id.get().getBuyersupplyprice())
                    .surtax(id.get().getSurtax())
                    .salesprice(id.get().getSalesprice())
                    .useyn(deactivateItemPriceDTO.getUseYn())
                    .build();
            itemPriceRepository.save(itemPrice);
        }
        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 판매부번 조회 dto 변경
     *
     * @param item
     * @return
     */
    private ItemListDTO entityToDto(Item item) {
        return ItemListDTO.builder()
                .itemId(item.getItemid())
                .itemCd(item.getItemcd())
                .itemNm(item.getItemnm())
                .originPrice(item.getOriginprice())
                .supplyPrice(item.getSupplyprice())
                .unit(item.getUnit())
                .stock(item.getStock())
                .useYn(item.getUseyn())
                .addDate(item.getAdddate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }

    /**
     * 판매가격 조회 dto 변경
     *
     * @param itemPrice
     * @param searchItem
     * @param buyernm
     * @return
     */
    private ItemPriceListDTO entityToDto(ItemPrice itemPrice, SearchItemDTO searchItem, String buyernm) {

        return ItemPriceListDTO.builder()
                .itemPriceId(itemPrice.getItempriceid())
                .buyerCd(itemPrice.getBuyercd())
                .buyerNm(buyernm)
                .itemCd(itemPrice.getItemcd())
                .itemNm(searchItem.getItemNm())
                .originPrice(searchItem.getOriginPrice())
                .buyerSupplyPrice(itemPrice.getBuyersupplyprice())
                .surtax(itemPrice.getSurtax())
                .salesPrice(itemPrice.getSalesprice())
                .unit(searchItem.getUnit())
                .addDate(itemPrice.getAdddate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .useYn(itemPrice.getUseyn())
                .build();
    }
}
