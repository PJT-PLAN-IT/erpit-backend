package com.pjt.erpit.biz.dto.Report;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private String revenue; //연누계

    private Integer orderCount; //월별 총 주문건수

    private Integer orderPrice; //월별 총 주문금액

    private List<ChartDto> chart;   //차트

    private List<TopSalesDto> topSalesList; //월별 제품매출 top5

    private List<TopUserDto> topUsersList;  //월별 영원사원 매출 Top10

    private List<TopBuyerDto> topBuyerList; //월별 바이어 매출 Top10

    @Data
    public static class ChartDto {
        private String month;
        private String sales;
    }

    @Data
    public static class TopSalesDto {
        private String itemNm;
        private Integer itemSales;
    }

    @Data
    public static class TopUserDto {
        private String userCd;
        private String userNm;
        private Integer userSales;
    }

    @Data
    public static class TopBuyerDto {
        private String buyerCd;
        private String buyerNm;
        private Integer buyerSales;
    }
}
