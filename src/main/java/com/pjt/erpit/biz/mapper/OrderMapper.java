package com.pjt.erpit.biz.mapper;

import com.pjt.erpit.biz.dto.Order.OrderDetailListDto;
import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import com.pjt.erpit.biz.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 오더 관련 Mapper
 */
@SuppressWarnings("SpellCheckingInspection")
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

    /**
     * 오더 디테일 조회
     *
     * @param orderno p1
     * @return OrderListDTO.Response
     */
    OrderDetailListDto.Response getOrderDetail(Long orderno);

    /**
     * 오더 아이템 리스트 조회
     *
     * @param orderno p1
     * @return List<OrderDetailListDto.Detail>
     */
    List<OrderDetailListDto.Detail> getOrderItemList(Long orderno);
}
