package com.pjt.erpit.biz.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 비밀번호 재설정 DTO
 */
public class ChangePasswordDTO {
    @Data
    public static class Request {
        @NotNull
        private String password;
    }
}
