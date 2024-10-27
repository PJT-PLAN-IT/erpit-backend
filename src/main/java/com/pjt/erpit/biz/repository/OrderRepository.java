package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderid(Long orderid);
}
