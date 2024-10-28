package com.pjt.erpit.biz.dto.item;

import com.pjt.erpit.biz.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemDTO {

    @NotNull
    private Long itemId;

    @NotNull
    private String itemCd;

    @NotNull
    private String itemNm;

    @NotNull
    private Integer originPrice;

    @NotNull
    private Integer supplyPrice;

    @NotNull
    private String unit;

    @NotNull
    private Integer stock;

    @NotNull
    private String useYn;

    public Item toEntity() {
        return Item.builder()
                .itemid(itemId)
                .itemcd(itemCd)
                .itemnm(itemNm)
                .originprice(originPrice)
                .supplyprice(supplyPrice)
                .unit(unit)
                .stock(stock)
                .useyn(useYn)
                .build();
    }
}
