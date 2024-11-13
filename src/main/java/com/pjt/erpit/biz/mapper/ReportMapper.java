package com.pjt.erpit.biz.mapper;

import com.pjt.erpit.biz.dto.Report.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 리포트 관련 Mapper
 */
@Mapper
public interface ReportMapper {

    List<ReportDTO.TopUserDto> top10Users(@Param("year") int year, @Param("month") int month);

    List<ReportDTO.TopBuyerDto> top10Buyers(@Param("year") int year, @Param("month") int month, @Param("user") String user);
}
