package com.pjt.erpit.biz.dto.buyer;

import com.pjt.erpit.biz.entity.Buyer;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 바이어 수정
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBuyerDTO {

    @NotNull
    private Long buyerId;

    @NotNull
    private String buyerCd;

    @NotNull
    private String buyerNm;

    @NotNull
    private String tel;

    @NotNull
    private String email;

    @NotNull
    private String zipcode;

    @NotNull
    private String address;

    @NotNull
    private String addressDetail;

    public Buyer toEntity() {
        return Buyer.builder()
                .buyerid(buyerId)
                .buyercd(buyerCd)
                .buyernm(buyerNm)
                .tel(tel)
                .email(email)
                .zipcode(zipcode)
                .address(address)
                .addressdetail(addressDetail)
                .build();
    }
}
