package com.pjt.erpit.biz.dto.item;

import com.pjt.erpit.biz.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateItemDTO {

    @NotNull
    private Long itemid;

    @NotNull
    private String useyn;
}
