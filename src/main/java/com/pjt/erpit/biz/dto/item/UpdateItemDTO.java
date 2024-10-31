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

    public Item toEntity(Item item1,  String ip) {
        Item item = item1.builder()
                .itemid(itemid)
                .itemcd(itemcd)
                .itemnm(itemnm)
                .originprice(originprice)
                .supplyprice(supplyprice)
                .unit(unit)
                .stock(stock)
                .useyn(useyn)
                .build();
        item.setUpdipaddr(ip);
        item.setUpduser(item1.getUpduser());
        item.setAdduser(item1.getAdduser());
        item.setAddipaddr(item1.getAddipaddr());

        return item;
    }
}
