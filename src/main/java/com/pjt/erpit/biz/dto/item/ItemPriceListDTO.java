package com.pjt.erpit.biz.dto.item;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceListDTO {

    private Long itempriceid;

    private String buyercd;

    private String buyernm;

    private String itemcd;

    private String itemnm;

    private Integer originprice;

    private Integer buyersupplyprice;

    private Integer surtax;

    private Integer salesprice;

    private String unit;

    private String adddate;

    private Integer stock;

    private String useyn;


}
