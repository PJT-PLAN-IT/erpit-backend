package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Item Price Repository
 */
@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long> {
    ItemPrice findByBuyercdAndItemcd(String buyercd, String itemcd);

    @Query("SELECT ip FROM M_ITEM_PRICE ip " +
            "WHERE (LOWER(:item) IS NULL OR LOWER(:item) = '' OR LOWER(ip.itemcd) LIKE LOWER(CONCAT('%', :item, '%'))) " +
            "OR (LOWER(:buyer) IS NULL OR LOWER(:buyer) = '' OR LOWER(ip.buyercd) LIKE LOWER(CONCAT('%', :buyer, '%')))")
    List<ItemPrice> searchItemPrice(@Param("item") String item, @Param("buyer") String buyer);
}
