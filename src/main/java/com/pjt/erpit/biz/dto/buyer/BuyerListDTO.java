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
public class BuyerListDTO {

    @NotNull
    private Long buyerid;

    @NotNull
    private String buyercd;

    @NotNull
    private String buyernm;

    @NotNull
    private String tel;

    @NotNull
    private String email;

    @NotNull
    private String zipcode;

    @NotNull
    private String address;

    @NotNull
    private String adddate;
}
