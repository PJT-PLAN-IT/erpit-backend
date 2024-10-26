package com.example.bot.biz.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 오더 리스트 조회 DTO
 */
public class OrderListDTO {
    @Data
    public static class Request {
        private String buyer;

        private String status;

        @NotNull
        private String year;

        @NotNull
        private String month;
    }

    @Data
    public static class Response {

    }
}
