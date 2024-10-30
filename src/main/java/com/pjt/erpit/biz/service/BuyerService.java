package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.buyer.BuyerListDTO;
import com.pjt.erpit.biz.dto.buyer.CheckDuplicationDTO;
import com.pjt.erpit.biz.dto.buyer.CreateBuyerDTO;
import com.pjt.erpit.biz.dto.buyer.UpdateBuyerDTO;
import com.pjt.erpit.biz.entity.Buyer;
import com.pjt.erpit.biz.repository.BuyerRepository;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 바이어 관련 Service
 */
@SuppressWarnings({"CallToPrintStackTrace", "ExtractMethodRecommender", "SpellCheckingInspection"})
@Slf4j
@Service
@Transactional(readOnly = true)
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    /**
     * 바이어코드 중복 검사
     *
     * @param buyercd p1
     * @return CheckDuplicationDTO.Response
     */
    public CheckDuplicationDTO.Response checkDuplication(String buyercd) {
        CheckDuplicationDTO.Response response = new CheckDuplicationDTO.Response();
        response.setIsDuplication(buyerRepository.existsByBuyercd(buyercd));
        return response;
    }

    /**
     * 바이어 생성
     *
     * @param createBuyerDTO p1
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> createBuyer(HttpServletRequest request, @Valid CreateBuyerDTO.Request createBuyerDTO) {
        if (buyerRepository.existsByBuyercd(createBuyerDTO.getBuyercd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "is already exist");
        }

        String ip = request.getRemoteAddr();
        Buyer buyer = new Buyer();
        buyer.setBuyercd(createBuyerDTO.getBuyercd());
        buyer.setBuyernm(createBuyerDTO.getBuyernm());
        buyer.setTel(createBuyerDTO.getTel());
        buyer.setEmail(createBuyerDTO.getEmail());
        buyer.setZipcode(createBuyerDTO.getZipcode());
        buyer.setAddress(createBuyerDTO.getAddress());
        buyer.setAddressdetail(createBuyerDTO.getAddressdetail());
        buyer.setAddipaddr(ip);
        buyer.setUpdipaddr(ip);

        try {
            buyerRepository.save(buyer);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 바이어 조회
     *
     * @param buyer
     * @return
     */
    public List<BuyerListDTO> buyerList(String buyer) {
        List<Buyer> buyerlist;

        if (buyer == null || buyer.isEmpty()) {
            buyerlist = buyerRepository.findAll();
        } else {
            buyerlist = buyerRepository.findByBuyercdOrBuyernm(buyer);
        }

        List<BuyerListDTO> result = buyerlist.stream()
                .map(b -> {
                    BuyerListDTO dto = entityToDto(b);
                    return dto;
                })
                .toList();
        return result;
    }

    /**
     * 바이어 수정
     *
     * @param updateBuyerDTO
     * @return
     */
    @Transactional
    public ResponseResult<?> updateBuyer(UpdateBuyerDTO updateBuyerDTO) {
        Buyer buyer = updateBuyerDTO.toEntity();
        buyerRepository.save(buyer);
        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 바이어 조회 dto 변경
     *
     * @param buyer
     * @return
     */
    private BuyerListDTO entityToDto(Buyer buyer) {
        return BuyerListDTO.builder()
                .buyerid(buyer.getBuyerid())
                .buyercd(buyer.getBuyercd())
                .buyernm(buyer.getBuyernm())
                .tel(buyer.getTel())
                .email(buyer.getEmail())
                .zipcode(buyer.getZipcode())
                .address(buyer.getAddress())
                .addressdetail(buyer.getAddressdetail())
                .adddate(buyer.getAdddate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }
}
