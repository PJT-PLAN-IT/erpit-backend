package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.Order.CreateOrderDTO;
import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import com.pjt.erpit.biz.service.OrderService;
import com.pjt.erpit.core.config.ResponseResult;
import com.pjt.erpit.core.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 오더 관련 Controller
 * auth: admin, user
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final JwtUtil jwtUtil;
    private final OrderService orderService;

    public OrderController(JwtUtil jwtUtil, OrderService orderService) {
        this.jwtUtil = jwtUtil;
        this.orderService = orderService;
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
     * @param request        p1
     * @param createOrderDTO p2
     * @return ResponseResult<?>
     */
    @PostMapping
    public ResponseResult<?> createOrder(HttpServletRequest request, @Valid @RequestBody CreateOrderDTO.Request createOrderDTO) {
        return orderService.createOrder(request, createOrderDTO);
    }
}
