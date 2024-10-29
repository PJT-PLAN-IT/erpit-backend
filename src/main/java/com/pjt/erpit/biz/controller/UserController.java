package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.user.ChangePasswordDTO;
import com.pjt.erpit.biz.dto.user.UserDto;
import com.pjt.erpit.biz.dto.user.UserListDto;
import com.pjt.erpit.biz.service.AuthService;
import com.pjt.erpit.biz.service.UserService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseResult<?> userList(@RequestParam String user) {
        return ResponseResult.ofSuccess("success", userService.getUserList(user));
    }

    @PutMapping
    public ResponseResult<?> updateUser(HttpServletRequest request, @Valid @RequestBody UserDto.Request user) {
        return userService.updateUser(request, user);
    }
}
