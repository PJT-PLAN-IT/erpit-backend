package com.pjt.erpit.biz.entity;

import com.pjt.erpit.biz.dto.buyer.UpdateBuyerDTO;
import com.pjt.erpit.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 바이어 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_BUYER")
public class Buyer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyerid")
    private Long buyerid;  // 바이어ID

    @Column(name = "buyercd")
    private String buyercd;  // 바이어코드

    @Column(name = "buyernm")
    private String buyernm;  // 바이어명

    @Column(name = "tel")
    private String tel;  // 전화번호

    @Column(name = "email")
    private String email;  // 이메일

    @Column(name = "zipcode")
    private String zipcode;  // 우편번호

    @Column(name = "address")
    private String address;  // 주소

    @Column(name = "addressdetail")
    private String addressdetail;  // 상세주소

    public void updateBuyer(UpdateBuyerDTO updateBuyerDto) {
        if (updateBuyerDto.getBuyerCd() != null) {
            this.buyercd = updateBuyerDto.getBuyerCd();
        }
        if (updateBuyerDto.getBuyerNm() != null) {
            this.buyernm = updateBuyerDto.getBuyerNm();
        }
        if (updateBuyerDto.getTel() != null) {
            this.tel = updateBuyerDto.getTel();
        }
        if (updateBuyerDto.getEmail() != null) {
            this.email = updateBuyerDto.getEmail();
        }
        if (updateBuyerDto.getZipcode() != null) {
            this.zipcode = updateBuyerDto.getZipcode();
        }
        if (updateBuyerDto.getAddress() != null) {
            this.address = updateBuyerDto.getAddress();
        }
        if (updateBuyerDto.getAddressDetail() != null) {
            this.addressdetail = updateBuyerDto.getAddressDetail();
        }
    }
}
