package com.pjt.erpit.biz.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 오더 리스트 조회 DTO
 */
public class OrderListDTO {
    @Data
    public static class Request {

        private String user;

        private String buyer;

        private String status;

        @NotNull
        private String year;

        @NotNull
        private String month;

        private LocalDateTime startDate;

        private LocalDateTime endDate;
    }

    @Data
    public static class Response {
        private Integer orderid;

        private Integer orderno ;

        private String orderdate ;

        private String usercd;

        private String usernm;

        private String buyercode;

        private String buyernm;

        private String adddate;

        private String requestdate;

        private String status;
    }
}
