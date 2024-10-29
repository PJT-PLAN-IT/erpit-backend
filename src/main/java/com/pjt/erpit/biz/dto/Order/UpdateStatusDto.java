package com.pjt.erpit.biz.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class UpdateStatusDto {

    @Data
    public static class Request {

        @NotNull
        private Long orderno;

        @NotNull
        private String status;

        @NotNull
        private String rejectcode;

        @NotNull
        private String rejectreason;

    }

}
