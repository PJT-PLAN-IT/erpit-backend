package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.admin.ResetPasswordDTO;
import com.pjt.erpit.biz.dto.admin.SignupDTO;
import com.pjt.erpit.biz.service.AdminService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 어드민 관련 Controller
 * auth: admin
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 사원 생성
     *
     * @param signupDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping("/signup")
    public ResponseResult<?> signup(@Valid @RequestBody SignupDTO.Request signupDTO) {
        adminService.signup(signupDTO);

        return ResponseResult.ofSuccess("success", null);
    }

    /**
     * 비밀번호 초기화
     *
     * @param resetPasswordDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping("/reset-password")
    public ResponseResult<?> resetPassword(@Valid @RequestBody ResetPasswordDTO.Request resetPasswordDTO) {
        return ResponseResult.ofSuccess("success", null);
    }
}
