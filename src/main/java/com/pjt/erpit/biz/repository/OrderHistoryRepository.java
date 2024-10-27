package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.history.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order History Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
