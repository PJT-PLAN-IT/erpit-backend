package com.pjt.erpit.biz.mapper;

import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import com.pjt.erpit.biz.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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

    /**
     * 오더 생성
     *
     * @param order p1
     */
    void createOrder(Order order);
}
