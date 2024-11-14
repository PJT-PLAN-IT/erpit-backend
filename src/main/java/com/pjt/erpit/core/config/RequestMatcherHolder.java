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
            "/api/buyer",
            "/api/buyer/list",
            "/api/buyer/check",
            "/api/admin/signup",
            "/api/user/change-password",
            "/api/user/list",
            "/api/item",
            "/api/item/check",
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
            "/api/user/change-password",
            "/api/item",
            "/api/item/**"
    );
}
