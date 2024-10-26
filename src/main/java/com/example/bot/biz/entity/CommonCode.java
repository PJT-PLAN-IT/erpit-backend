package com.example.bot.biz.entity;

import com.example.bot.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 공통코드 마스터 테이블
 */
@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_COMMON_CODE")
public class CommonCode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commoncodeid")
    private Long commoncodeid;  // 공통코드ID

    @Column(name = "code")
    private Long code;  // 코드

    @Column(name = "uppercode")
    private Long uppercode;  // 상위코드

    @Column(name = "codenm")
    private Long codenm;  // 코드명

    @Column(name = "comment")
    private Long comment;  // 설명
}
