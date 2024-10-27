package com.pjt.erpit.biz.dto.buyer;

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
    private Long buyerId;

    private String buyerCd;

    private String buyerNm;

    private String tel;

    private String email;

    private String zipcode;

    private String address;

    private String addressDetail;
}
