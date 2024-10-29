package com.pjt.erpit.biz.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 오더 수정 DTO
 */
@SuppressWarnings("SpellCheckingInspection")
public class UpdateOrderDTO {
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Request extends CreateOrderDTO.Request {
        @NotNull
        private Long orderno;
    }
}
