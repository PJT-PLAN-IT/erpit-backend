package com.pjt.erpit.biz.controller;

import com.pjt.erpit.biz.dto.Order.CreateOrderDTO;
import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import com.pjt.erpit.biz.dto.Order.UpdateOrderDTO;
import com.pjt.erpit.biz.dto.Order.UpdateStatusDto;
import com.pjt.erpit.biz.service.OrderService;
import com.pjt.erpit.core.config.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 오더 관련 Controller
 * auth: admin, user
 */
@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 오더 리스트 조회
     *
     * @param orderListDTO p2
     * @return ResponseResult<?>
     */
    @PostMapping("/list")
    public ResponseResult<?> getOrderList(@Valid @RequestBody OrderListDTO.Request orderListDTO) {
        return orderService.orderList(orderListDTO);
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

    /**
     * 오더 디테일 조회
     *
     * @param orderno p1
     * @return ResponseResult<?>
     */
    @GetMapping("/detail/{orderno}")
    public ResponseResult<?> getOrderDetailList(@Valid @PathVariable Long orderno) {
        return ResponseResult.ofSuccess("success", orderService.getOrderDetailList(orderno));
    }

    /**
     * 오더 수정
     * @param request p1
     * @param updateOrderDto p2
     * @return  ResponseResult
     */
    @PutMapping
    public ResponseResult<?> updateOrder(HttpServletRequest request, @Valid @RequestBody UpdateOrderDTO.Request updateOrderDto) {
        return orderService.updateOrder(request, updateOrderDto);
    }

    /**
     * 상태 변경
     * @param request p1
     * @param updateStatusDto p2
     * @return ResponseREsult
     */
    @PutMapping("/status")
    public ResponseResult<?> updateStatus(HttpServletRequest request, @Valid @RequestBody UpdateStatusDto.Request updateStatusDto) {
        return orderService.updateStatus(request, updateStatusDto);
    }


}
