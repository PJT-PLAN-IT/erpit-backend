package com.pjt.erpit.biz.dto.item;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceListDTO {

    private Long itemPriceId;

    private String buyerCd;

    private String buyerNm;

    private String itemCd;

    private String itemNm;

    private Integer originPrice;

    private Integer buyerSupplyPrice;

    private Integer surtax;

    private Integer salesPrice;

    private String unit;

    private Integer stock;

    private String addDate;

    private String useYn;


}
