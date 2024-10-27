package com.pjt.erpit.biz.dto.buyer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 바이어 리스트 DTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerListDto {

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
    private String zipCode;

    @NotNull
    private String address;

    @NotNull
    private String addDate;
}
