package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Buyer Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    @Query("SELECT b FROM M_BUYER b WHERE LOWER(b.buyercd) LIKE LOWER(CONCAT('%', :buyer, '%')) OR LOWER(b.buyernm) LIKE LOWER(CONCAT('%', :buyer, '%'))")
    List<Buyer> findByBuyercdOrBuyernm(@Param("buyer") String buyer);

    Boolean existsByBuyercd(String buyercd);
}
