package com.example.bot.biz.controller;

import com.example.bot.biz.dto.Order.CreateOrderDTO;
import com.example.bot.biz.dto.Order.OrderListDTO;
import com.example.bot.core.config.ResponseResult;
import com.example.bot.core.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final JwtUtil jwtUtil;

    public OrderController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * 오더 리스트 조회
     *
     * @param request      p1
     * @param orderListDTO p2
     * @return ResponseResult<?>
     */
    @PostMapping("/list")
    public ResponseResult<?> getOrderList(HttpServletRequest request, @Valid @RequestBody OrderListDTO.Request orderListDTO) {
        String role = jwtUtil.getRoleByCookie(request);

        return null;
    }

    /**
     * 오더 생성
     *
     * @param createOrderDTO p1
     * @return ResponseResult<?>
     */
    @PostMapping
    public ResponseResult<?> createOrder(@Valid @RequestBody CreateOrderDTO.Request createOrderDTO) {
        return null;
    }
}
