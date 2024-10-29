package com.pjt.erpit.biz.dto.Report;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSearchDTO {

    private String year;
    private String month;
    private String user;
}
