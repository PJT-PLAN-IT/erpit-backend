package com.example.bot.biz.service;

import com.example.bot.biz.dto.Order.CreateOrderDTO;
import com.example.bot.core.config.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public ResponseResult<?> createOrder(CreateOrderDTO.Request createOrderDTO) {

        return ResponseResult.ofSuccess("success", null);
    }
}
