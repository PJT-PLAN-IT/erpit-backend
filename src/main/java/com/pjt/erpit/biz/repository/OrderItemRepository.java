package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Item Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}