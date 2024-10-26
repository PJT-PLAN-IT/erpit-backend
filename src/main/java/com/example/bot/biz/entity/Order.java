package com.example.bot.biz.entity;

import com.example.bot.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 오더 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_ORDER")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderid;  // 오더ID

    @Column(name = "orderno")
    private String orderno;  // 오더번호

    @Column(name = "orderdate")
    private LocalDateTime orderdate;  // 오더일자

    @Column(name = "usercd")
    private String usercd;  // 사원코드

    @Column(name = "buyercd")
    private String buyercd;  // 바이어코드

    @Column(name = "status")
    private String status;  // 오더상태

    @Column(name = "rejectcode")
    private String rejectcode;  // 거절상태

    @Column(name = "rejectreason")
    private String rejectreason;  // 거절사유
}
