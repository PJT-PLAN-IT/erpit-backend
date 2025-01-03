package com.pjt.erpit.biz.entity;

import com.pjt.erpit.biz.entity.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 권한 마스터 테이블
 */
@SuppressWarnings({"JpaDataSourceORMInspection", "SpellCheckingInspection"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "M_AUTH")
public class Auth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authid")
    private Long authid;  // 권한ID

    @Column(name = "usercd")
    private String usercd;  // 사원코드

    @Column(name = "auth")
    private String auth = "ROLE_USER"; // 권한
}
