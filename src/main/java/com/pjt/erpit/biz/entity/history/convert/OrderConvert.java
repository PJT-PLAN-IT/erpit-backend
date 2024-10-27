package com.pjt.erpit.biz.entity.history.convert;

import com.pjt.erpit.biz.entity.Order;
import com.pjt.erpit.biz.entity.history.OrderHistory;
import org.mapstruct.Mapper;

/**
 * Order History Convert
 */
@Mapper(componentModel = "spring")
public interface OrderConvert {
    OrderHistory toOrderHistory(Order order);
}
