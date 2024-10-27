package com.pjt.erpit.biz.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 판매부번 생성 DTO
 */
@SuppressWarnings("SpellCheckingInspection")
public class CreateItemPriceDTO {
    @Data
    public static class Request {
        @NotNull
        private String buyercd;

        @NotNull
        private String itemcd;

        @NotNull
        private Integer buyersupplyprice;

        @NotNull
        private Integer surtax;

        @NotNull
        private Integer salesprice;
    }
}
