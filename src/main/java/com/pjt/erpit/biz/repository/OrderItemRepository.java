package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order Item Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderno(Long orderno);

    void deleteAllByOrderno(Long orderno);
}
