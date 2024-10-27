package com.pjt.erpit.biz.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 오더 생성 DTO
 */
@SuppressWarnings("SpellCheckingInspection")
public class CreateOrderDTO {
    @Data
    public static class Request {
        @NotNull
        private String orderdate;

        @NotNull
        private String usercd;

        @NotNull
        private String buyercd;

        @NotNull
        private String status;

        @NotNull
        private List<OrderItemList> orderItemList;
    }

    @Data
    public static class OrderItemList {
        @NotNull
        private String itemcd;

        @NotNull
        private Integer ordersupplyprice;

        @NotNull
        private Integer ordersurtax;

        @NotNull
        private Integer ordersalesprice;

        @NotNull
        private Integer orderqty;

        @NotNull
        private String deliverydate;
    }
}
