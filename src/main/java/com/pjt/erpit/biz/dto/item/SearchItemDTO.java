package com.pjt.erpit.biz.dto.item;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemDTO {

    private String itemNm;
    private Integer originPrice;
    private String unit;
    private Integer stock;
}
