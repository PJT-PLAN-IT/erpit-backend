package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderid(Long orderid);

    @Query("select o FROM M_ORDER o WHERE o.status = 'APRV_CMPT' " +
            "AND o.orderdate >= :startDate AND o.orderdate < :endDate " +
            "AND (:usercd IS NULL OR :usercd = '' OR o.usercd = :usercd)")
    List<Order> findByOrder(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("usercd") String usercd);

}
