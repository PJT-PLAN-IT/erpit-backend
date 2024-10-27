package com.pjt.erpit.biz.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 판매부번 생성 DTO
 */
@SuppressWarnings("SpellCheckingInspection")
public class CreateItemDTO {
    @Data
    public static class Request {
        @NotNull
        private String itemcd;

        @NotNull
        private String itemnm;

        @NotNull
        private Integer originprice;

        @NotNull
        private Integer supplyprice;

        @NotNull
        private Integer stock;

        @NotNull
        private String unit;
    }
}
