package com.pjt.erpit.biz.dto.buyer;

import lombok.Data;

/**
 * 바이어코드 중복 검사 DTO
 */
public class CheckDuplicationDTO {
    @Data
    public static class Response {
        private Boolean isDuplication;
    }
}
