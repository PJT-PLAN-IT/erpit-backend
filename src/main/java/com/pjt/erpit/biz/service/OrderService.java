package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.Order.CreateOrderDTO;
import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.Order;
import com.pjt.erpit.biz.entity.OrderItem;
import com.pjt.erpit.biz.entity.history.OrderHistory;
import com.pjt.erpit.biz.entity.history.convert.OrderConvert;
import com.pjt.erpit.biz.mapper.OrderMapper;
import com.pjt.erpit.biz.repository.*;
import com.pjt.erpit.core.config.ResponseResult;
import com.pjt.erpit.core.util.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 오더 관련 Service
 */
@Slf4j
@SuppressWarnings({"SpellCheckingInspection", "CallToPrintStackTrace", "ExtractMethodRecommender"})
@Service
public class OrderService {
    private final UserRepository userRepository;
    private final BuyerRepository buyerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderMapper orderMapper;
    private final OrderConvert orderConvert;
    private final OrderItemRepository orderItemRepository;

    public OrderService(UserRepository userRepository, BuyerRepository buyerRepository, ItemRepository itemRepository, OrderHistoryRepository orderHistoryRepository, OrderMapper orderMapper, OrderRepository orderRepository, OrderConvert orderConvert, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.itemRepository = itemRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderConvert = orderConvert;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * 오더 생성
     *
     * @param request        p1
     * @param createOrderDTO p2
     * @return ResponseResult<?>
     */
    @Transactional
    public ResponseResult<?> createOrder(HttpServletRequest request, CreateOrderDTO.Request createOrderDTO) {
        if (createOrderDTO.getOrderItemList() == null || createOrderDTO.getOrderItemList().isEmpty()) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "empty order item list");
        }
        if (!userRepository.existsByUsercd(createOrderDTO.getUsercd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "user is not exist");
        }
        if (!buyerRepository.existsByBuyercd(createOrderDTO.getBuyercd())) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "buyercd is not exist");
        }

        List<CreateOrderDTO.OrderItem> orderItemList = createOrderDTO.getOrderItemList();
        for (CreateOrderDTO.OrderItem orderItem : orderItemList) {
            Item item = itemRepository.findByItemcd(orderItem.getItemcd());
            if (item == null) {
                return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "item is not exist");
            }

            if (item.getStock() < orderItem.getOrderqty()) {
                return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, item.getItemnm() + " stock less than orderqty");
            }
        }

        String ip = request.getRemoteAddr();

        Order order = new Order();
        order.setOrderdate(DateUtils.toLocalDate(createOrderDTO.getOrderdate()));
        order.setUsercd(createOrderDTO.getUsercd());
        order.setBuyercd(createOrderDTO.getBuyercd());
        order.setStatus(createOrderDTO.getStatus());
        order.setAddipaddr(ip);
        order.setAdduser(createOrderDTO.getUsercd());
        order.setAdddate(LocalDateTime.now());
        order.setUpdipaddr(ip);
        order.setUpduser(createOrderDTO.getUsercd());
        order.setUpddate(LocalDateTime.now());

        orderMapper.createOrder(order);

        if (order.getOrderid() == null) {
            return ResponseResult.ofFailure(HttpStatus.BAD_REQUEST, "orderid is null");
        }

        Order savedOrder = orderRepository.findByOrderid(order.getOrderid());
        OrderHistory orderHistory = orderConvert.toOrderHistory(savedOrder);

        try {
            orderHistoryRepository.save(orderHistory);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        List<OrderItem> saveOrderItemList = new ArrayList<>();
        for (CreateOrderDTO.OrderItem orderItem : orderItemList) {
            OrderItem saveOrderItem = new OrderItem();
            saveOrderItem.setOrderno(savedOrder.getOrderno());
            saveOrderItem.setItemcd(orderItem.getItemcd());
            saveOrderItem.setOrdersupplyprice(orderItem.getOrdersupplyprice());
            saveOrderItem.setOrdersurtax(orderItem.getOrdersurtax());
            saveOrderItem.setOrdersalesprice(orderItem.getOrdersalesprice());
            saveOrderItem.setOrderqty(orderItem.getOrderqty());
            saveOrderItem.setDeliverydate(DateUtils.toLocalDate(orderItem.getDeliverydate()));
            saveOrderItem.setAddipaddr(ip);
            saveOrderItem.setUpdipaddr(ip);
            saveOrderItemList.add(saveOrderItem);
        }

        try {
            orderItemRepository.saveAll(saveOrderItemList);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResponseResult.ofFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseResult.ofSuccess("success", null);
    }
}
