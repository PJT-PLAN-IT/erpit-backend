package com.pjt.erpit.biz.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemListDTO {

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

    @NotNull
    private String addDate;
}
