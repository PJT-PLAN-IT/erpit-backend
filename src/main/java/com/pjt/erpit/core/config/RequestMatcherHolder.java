package com.pjt.erpit.core.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 페이지별 권한 설정
 */
@Getter
@Setter
public class RequestMatcherHolder {
    private final List<String> PERMIT_ALL_URLS = List.of(
            "/api/auth",
            "/api/auth/**",
            "/api/test/**"
    );
    private final List<String> PERMIT_USER_URLS = List.of(
            "/api/order",
            "/api/order/**",
            "/api/report",
            "/api/report/**"
    );
    private final List<String> PERMIT_ADMIN_URLS = List.of(
            "/api/admin",
            "/api/admin/**",
            "/api/buyer",
            "/api/buyer/**",
            "/api/item",
            "/api/item/**"
    );
}
