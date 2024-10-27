package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.buyer.BuyerListDto;
import com.pjt.erpit.biz.dto.buyer.CreateBuyerDTO;
import com.pjt.erpit.biz.entity.Buyer;
import com.pjt.erpit.biz.repository.BuyerRepository;
import com.pjt.erpit.core.config.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 바이어 관련 Service
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BuyerService {

    private final BuyerRepository buyerRepository;

    /**
     * @param createBuyerDTO p1
     * @return ResponseResult<?>
     */
    public ResponseResult<?> createBuyer(CreateBuyerDTO.Request createBuyerDTO) {
        return null;
    }

    /**
     * 바이어 조회
     * @param buyer
     * @return
     */
    public List<BuyerListDto> buyerList(String buyer) {
        List<Buyer> buyerlist = buyerRepository.findByBuyercdOrBuyernm(buyer);
        List<BuyerListDto> result = buyerlist.stream()
                .map(b -> {
                    BuyerListDto dto = entityToDto(b);
                    return dto;
                })
                .toList();
        return result;
    }

    /**
     * 바이어 조회 dto 변경
     * @param buyer
     * @return
     */
    private BuyerListDto entityToDto(Buyer buyer) {
        return BuyerListDto.builder()
                .buyerId(buyer.getBuyerid())
                .buyerCd(buyer.getBuyercd())
                .buyerNm(buyer.getBuyernm())
                .tel(buyer.getTel())
                .email(buyer.getEmail())
                .zipCode(buyer.getZipcode())
                .address(buyer.getAddress() + buyer.getAddressdetail())
                .addDate(buyer.getAdddate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }
}
