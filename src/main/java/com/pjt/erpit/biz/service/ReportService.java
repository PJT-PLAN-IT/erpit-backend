package com.pjt.erpit.biz.service;

import com.pjt.erpit.biz.dto.Report.ReportDTO;
import com.pjt.erpit.biz.dto.Report.ReportSearchDTO;
import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.Order;
import com.pjt.erpit.biz.entity.OrderItem;
import com.pjt.erpit.biz.mapper.ReportMapper;
import com.pjt.erpit.biz.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ReportMapper reportMapper;

    public ReportDTO report(ReportSearchDTO reportDTO) {
        String yearString = reportDTO.getYear();
        Integer year = Integer.parseInt(yearString);

        Integer month = Integer.parseInt(reportDTO.getMonth());

        LocalDateTime startDate = LocalDateTime.of(year, 1,1,0,0);
        LocalDateTime endDate = LocalDateTime.of(year + 1, 1,1,0,0);

        List<Order> orders = orderRepository.findByOrder(startDate, endDate, reportDTO.getUser());

        Integer revenue = Integer.valueOf(
                orders.stream()    //연누계
                .flatMap(o -> {
                    List<OrderItem> orderItems = orderItemRepository.findByOrderno(o.getOrderno());
                    return orderItems.stream()
                            .map(oi -> {
                                Integer ordersalesprice = oi.getOrdersalesprice();
                                Integer orderqty = oi.getOrderqty();
                                if (ordersalesprice != null && orderqty != null) {
                                    return ordersalesprice * orderqty;
                                } else {
                                    return 0;
                                }
                            });
                })
                .mapToInt(Integer::intValue)
                .sum()
        );

        List<Order> list = orders.stream()
                .filter(o -> o.getOrderdate().getMonthValue() == month)
                .toList();

        int orderCount = list.size();   //월별 총 주문건수

        int orderPrice = list.stream()  //월별 총 주문금액
                .flatMap(o -> {
                    List<OrderItem> orderItems = orderItemRepository.findByOrderno(o.getOrderno());
                    return orderItems.stream()
                            .map(oi -> {
                                Integer ordersalesprice = oi.getOrdersalesprice();
                                Integer orderqty = oi.getOrderqty();
                                if (ordersalesprice != null && orderqty != null) {
                                    return ordersalesprice * orderqty;
                                } else {
                                    return 0;
                                }
                            });
                })
                .mapToInt(Integer::intValue)
                .sum();

        Map<Integer, Integer> monthTotal = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getOrderdate().getMonthValue(),
                        Collectors.mapping(order -> {
                            List<OrderItem> orderItems = orderItemRepository.findByOrderno(order.getOrderno());
                            return orderItems.stream()
                                    .map(oi -> {
                                        Integer ordersalesprice = oi.getOrdersalesprice();
                                        Integer orderqty = oi.getOrderqty();
                                        return (ordersalesprice != null && orderqty != null) ? ordersalesprice * orderqty : 0;
                                    })
                                    .reduce(0, Integer::sum);
                        }, Collectors.reducing(0, Integer::sum))
                ));
        List<ReportDTO.ChartDto> chart = monthTotal.entrySet().stream() //차트
                .map(e -> {
                    ReportDTO.ChartDto dto = new ReportDTO.ChartDto();
                    dto.setMonth(String.valueOf(e.getKey()));
                    dto.setSales(String.valueOf(e.getValue()));
                    return dto;
                })
                .toList();

        Map<String, Integer> itemSalesMap = new HashMap<>();
        Map<String, String> itemCdMap = new HashMap<>();

        orders.stream()
                .filter(o -> o.getOrderdate().getMonthValue() == month)
                .forEach(o -> {
                    List<OrderItem> orderItems = orderItemRepository.findByOrderno(o.getOrderno());

                    orderItems.forEach(oi -> {
                        String itemCd = oi.getItemcd();
                        String itemNm = itemRepository.findByItemnm(itemCd);
                        Integer itemSales = oi.getOrdersalesprice() * oi.getOrderqty();

                        itemCdMap.put(itemNm, itemCd);

                        if (itemSalesMap.containsKey(itemNm)) {
                            itemSalesMap.put(itemNm, itemSalesMap.get(itemNm) + itemSales);
                        } else {
                            itemSalesMap.put(itemNm, itemSales);
                        }
                    });
                });

        List<ReportDTO.TopSalesDto> topSalesList = itemSalesMap.entrySet().stream()  //월별 제품매출 top5
                .map(e -> {
                    ReportDTO.TopSalesDto dto = new ReportDTO.TopSalesDto();
                    dto.setItemCd(itemCdMap.get(e.getKey()));
                    dto.setItemNm(e.getKey());
                    dto.setItemSales(e.getValue());
                    return dto;
                })
                .sorted(Comparator.comparing(ReportDTO.TopSalesDto::getItemSales).reversed())   //getItemSales 기준 내림차순
                .limit(7)
                .toList();

        List<ReportDTO.TopUserDto> topUsersList = reportMapper.top10Users(month);  //월별 영원사원 매출 Top10

        List<ReportDTO.TopBuyerDto> topBuyerList = reportMapper.top10Buyers(month);  //월별 바이어 매출 Top10

        return ReportDTO.builder()
                .revenue(revenue)
                .orderCount(orderCount)
                .orderPrice(orderPrice)
                .chart(chart)
                .topSalesList(topSalesList)
                .topUsersList(topUsersList)
                .topBuyerList(topBuyerList)
                .build();
    }
}

