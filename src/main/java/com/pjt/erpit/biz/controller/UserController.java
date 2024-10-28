package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.user.ChangePasswordDTO;
import com.pjt.erpit.biz.service.AuthService;
import com.pjt.erpit.biz.service.UserService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사원 관련 Controller
 * auth: admin, user
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 비밀번호 재설정
     *
     * @param changePasswordDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping("/change-password")
    public ResponseResult<?> changePassword(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody ChangePasswordDTO.Request changePasswordDTO) {
        return userService.changePassword(request, response, changePasswordDTO);
    }
}
