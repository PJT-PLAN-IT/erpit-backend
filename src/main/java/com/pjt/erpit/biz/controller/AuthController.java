package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.auth.LoginDTO;
import com.pjt.erpit.biz.service.AuthService;
import com.pjt.erpit.core.config.ResponseResult;
import com.pjt.erpit.core.security.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 권한 관련 Controller
 * auth: all
 */
@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 로그인
     *
     * @param response p1
     * @param loginDTO p2
     * @return ResponseResult<?>
     */
    @PostMapping("/login")
    public ResponseResult<?> login(HttpServletResponse response, @Valid @RequestBody LoginDTO.Request loginDTO) {
        return authService.login(response, loginDTO);
    }

    /**
     * 로그아웃
     *
     * @param request  p1
     * @param response p2
     * @return ResponseResult<?>
     */
    @PostMapping("/logout")
    public ResponseResult<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return authService.logout(request, response, jwtUtil.getUsercdByCookie(request));
    }

    /**
     * Access Token 재발급
     *
     * @param request  p1
     * @param response p2
     * @return ResponseResult<?>
     */
    @Operation(summary = "Access Token 재발급")
    @PostMapping("/refresh")
    public ResponseResult<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        return authService.refresh(request, response);
    }
}
