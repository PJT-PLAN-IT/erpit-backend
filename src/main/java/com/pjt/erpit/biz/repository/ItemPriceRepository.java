package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Item Price Repository
 */
@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long> {
    ItemPrice findByBuyercdAndItemcd(String buyercd, String itemcd);
}
