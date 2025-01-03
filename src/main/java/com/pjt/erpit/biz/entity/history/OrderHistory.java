package com.pjt.erpit.biz.entity.history;

import com.pjt.erpit.biz.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 오더 히스토리 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "H_ORDER")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderhistoryid")
    private Long orderhistoryid;  // 오더히스토리ID

    @Column(name = "orderid")
    private Long orderid;  // 오더ID

    @Column(name = "orderno")
    private String orderno;  // 오더번호

    @Column(name = "orderdate")
    private LocalDateTime orderdate = LocalDateTime.now();  // 오더일자

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

    @Column(name = "addipaddr")
    private String addipaddr = "0.0.0.0"; // 생성IP

    @Column(name = "adduser")
    private String adduser = ""; // 생성자

    @Column(name = "adddate")
    private LocalDateTime adddate = LocalDateTime.now(); // 생성일자

    @Column(name = "updipaddr")
    private String updipaddr = "0.0.0.0"; // 수정IP

    @Column(name = "upduser")
    private String upduser = ""; // 수정자

    @Column(name = "upddate")
    private LocalDateTime upddate = LocalDateTime.now(); // 수정일자
}
