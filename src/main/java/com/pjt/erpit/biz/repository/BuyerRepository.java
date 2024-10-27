package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Buyer Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Boolean existsByBuyercd(String buyercd);
}
