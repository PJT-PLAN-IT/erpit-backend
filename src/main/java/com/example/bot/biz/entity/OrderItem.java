package com.example.bot.biz.entity;

import com.example.bot.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 오더 아이템 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_ORDER_ITEM")
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitemid")
    private Long orderitemid;  // 오더아이템ID

    @Column(name = "orderno")
    private Long orderno;  // 오더번호

    @Column(name = "itemcd")
    private String itemcd;  // 판매부번코드

    @Column(name = "ordersupplyprice")
    private Integer ordersupplyprice;  // 공급가

    @Column(name = "ordersurtax")
    private Integer ordersurtax;  // 부가세

    @Column(name = "ordersalesprice")
    private Integer ordersalesprice;  // 판매가

    @Column(name = "orderqty")
    private Integer orderqty;  // 오더수량

    @Column(name = "deliverydate")
    private LocalDateTime deliverydate;  // 납품일자
}
