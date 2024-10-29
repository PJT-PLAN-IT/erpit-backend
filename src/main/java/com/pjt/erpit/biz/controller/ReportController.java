package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.Report.ReportDTO;
import com.pjt.erpit.biz.dto.Report.ReportSearchDTO;
import com.pjt.erpit.biz.service.ReportService;
import com.pjt.erpit.core.config.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 리포트 관련 Controller
 * auth: admin, user
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 리포트 조회
     * @param dto
     * @return
     */
    @GetMapping()
    public ResponseResult<?> report(@RequestBody ReportSearchDTO dto) {
        ReportDTO result = reportService.report(dto);
        return ResponseResult.ofSuccess("success", result);
    }

}
