package com.pjt.erpit.biz.dto.user;

import lombok.Data;

public class UserListDto {

    @Data
    public static class Response {

        private Long userid;
        private String usercd;
        private String usernm;
        private String birthdate;
        private String joindate;
        private String auth;
        private String adddate;

    }
}
