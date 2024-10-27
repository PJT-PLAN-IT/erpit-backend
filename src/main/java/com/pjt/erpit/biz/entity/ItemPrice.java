package com.pjt.erpit.biz.entity;

import com.pjt.erpit.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 판매가격 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_ITEM_PRICE")
public class ItemPrice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itempriceid")
    private Long itempriceid;  // 판매가격ID

    @Column(name = "buyercd")
    private String buyercd;  // 바이어코드

    @Column(name = "itemcd")
    private String itemcd;  // 판매부번코드

    @Column(name = "buyersupplyprice")
    private Integer buyersupplyprice = 0;  // 공급가

    @Column(name = "surtax")
    private Integer surtax = 0;  // 부가세

    @Column(name = "salesprice")
    private Integer salesprice = 0;  // 판매가

    @Column(name = "useyn")
    private String useyn = "Y";  // 사용여부
}
