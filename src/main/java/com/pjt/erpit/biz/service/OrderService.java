package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.Order.CreateOrderDTO;
import com.pjt.erpit.core.config.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * 오더 관련 Service
 */
@Service
public class OrderService {
    public ResponseResult<?> createOrder(CreateOrderDTO.Request createOrderDTO) {

        return ResponseResult.ofSuccess("success", null);
    }
}
