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
    private String addressdetail;

    public Buyer toEntity() {
        return Buyer.builder()
                .buyerid(buyerid)
                .buyercd(buyercd)
                .buyernm(buyernm)
                .tel(tel)
                .email(email)
                .zipcode(zipcode)
                .address(address)
                .addressdetail(addressdetail)
                .build();
    }
}
