package com.pjt.erpit.biz.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateItemPriceDTO {

    @NotNull
    private Long itemPriceId ;

    @NotNull
    private String useYn;
}
