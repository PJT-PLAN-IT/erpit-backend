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
    private Long itemid;

    @NotNull
    private String itemcd;

    @NotNull
    private String itemnm;

    @NotNull
    private Integer originprice;

    @NotNull
    private Integer supplyprice;

    @NotNull
    private String unit;

    @NotNull
    private Integer stock;

    @NotNull
    private String useyn;

    @NotNull
    private String adddate;
}
