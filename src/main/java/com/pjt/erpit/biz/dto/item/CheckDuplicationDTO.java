package com.pjt.erpit.biz.dto.item;

import lombok.Data;

/**
 * 판매부번코드 중복 검사 DTO
 */
public class CheckDuplicationDTO {
    @Data
    public static class Response {
        private Boolean isDuplication;
    }
}
