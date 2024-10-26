package com.example.bot.biz.dto.buyer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 바이어 생성 DTO
 */
@SuppressWarnings("SpellCheckingInspection")
public class CreateBuyerDTO {
    @Data
    public static class Request {
        @NotNull
        private String buyercd;

        @NotNull
        private String buyernm;

        @NotNull
        private String tel;

        @NotNull
        private String email;

        @NotNull
        private String zipcode;

        @NotNull
        private String address;

        @NotNull
        private String addressdetail;
    }
}
