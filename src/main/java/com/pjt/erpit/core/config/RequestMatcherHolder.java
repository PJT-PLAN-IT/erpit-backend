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
            "/api/report/**",
            "/api/buyer/list",
            "/api/user/list",
            "/api/item/list",
            "/api/item/price/list"
    );
    private final List<String> PERMIT_ADMIN_URLS = List.of(
            "/api/report",
            "/api/report/**",
            "/api/admin",
            "/api/admin/**",
            "/api/order",
            "/api/order/**",
            "/api/buyer",
            "/api/buyer/**",
            "/api/user",
            "/api/user/**",
            "/api/item",
            "/api/item/**"
    );
}
