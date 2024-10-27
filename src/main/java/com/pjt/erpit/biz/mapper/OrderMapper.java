package com.pjt.erpit.biz.mapper;

import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 오더 관련 Mapper
 */
@Mapper
public interface OrderMapper {
    /**
     * 오더 리스트 조회
     *
     * @param orderListDTO p1
     * @return List<OrderListDTO.Response>
     */
    List<OrderListDTO.Response> getOrderList(OrderListDTO.Request orderListDTO);
}
