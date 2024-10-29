package com.pjt.erpit.biz.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class UserDto {

    @Data
    public static class Request {

        @NotNull
        private String usercd;

        @NotNull
        private String usernm;

        @NotNull
        private String auth;


    }
}
