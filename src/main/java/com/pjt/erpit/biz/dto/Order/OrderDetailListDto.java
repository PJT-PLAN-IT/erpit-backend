package com.pjt.erpit.biz.dto.Order;

import lombok.Data;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class OrderDetailListDto {
    @Data
    public static class Response {
        private Integer orderid;

        private Integer orderno;

        private String orderdate;

        private String usercd;

        private String buyercd;

        private String buyernm;

        private String status;

        private String statusnm;

        private String rejectcode;

        private String rejectcodenm;

        private String rejectreason;

        private String adddate;

        private List<Detail> itemList;
    }

    @Data
    public static class Detail {
        private Integer orderitemid;

        private Integer orderno;

        private String itemcd;

        private String itemnm;

        private int orderqty;

        private int originprice;

        private Integer ordersupplyprice;

        private Integer ordersurtax;

        private Integer ordersalesprice;

        private int stock;
        private String unit;
        private String deliverydate;

    }

}
