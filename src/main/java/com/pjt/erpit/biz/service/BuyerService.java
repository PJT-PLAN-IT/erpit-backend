package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.buyer.CheckDuplicationDTO;
import com.pjt.erpit.biz.dto.buyer.CreateBuyerDTO;
import com.pjt.erpit.biz.entity.Buyer;
import com.pjt.erpit.biz.repository.BuyerRepository;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 바이어 관련 Service
 */
@SuppressWarnings({"CallToPrintStackTrace", "ExtractMethodRecommender", "SpellCheckingInspection"})
@Slf4j
@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    /**
     * 바이어코드 중복 검사
     *
     * @param buyercd p1
     * @return Boolean
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
    public ResponseResult<?> createBuyer(HttpServletRequest request, CreateBuyerDTO.Request createBuyerDTO) {
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
}
