package com.example.bot.biz.entity.history;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 판매부번 히스토리 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "H_ITEM")
public class ItemHistory {
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

    @Column(name = "addipaddr")
    private String addipaddr; // 생성IP

    @Column(name = "adduser")
    private String adduser; // 생성자

    @Column(name = "adddate")
    private LocalDateTime adddate; // 생성일자

    @Column(name = "updipaddr")
    private String updipaddr; // 수정IP

    @Column(name = "upduser")
    private String upduser; // 수정자

    @Column(name = "upddate")
    private LocalDateTime upddate; // 수정일자
}
