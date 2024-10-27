package com.pjt.erpit.biz.entity;

import com.pjt.erpit.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 판매부번 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_ITEM")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid")
    private Long itemid;  // 판매부번ID

    @Column(name = "itemcd")
    private String itemcd;  // 판매부번코드

    @Column(name = "itemnm")
    private String itemnm;  // 판매부번명

    @Column(name = "originprice")
    private Integer originprice;  // 원가

    @Column(name = "supplyprice")
    private Integer supplyprice;  // 공급가

    @Column(name = "unit")
    private String unit;  // 단위

    @Column(name = "stock")
    private Integer stock;  // 재고

    @Column(name = "useyn")
    private String useyn;  // 사용여부
}
