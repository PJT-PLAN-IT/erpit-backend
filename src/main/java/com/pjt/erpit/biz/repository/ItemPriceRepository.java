package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.ItemPrice;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Item Price Repository
 */
@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPrice, Long> {
    Optional<ItemPrice> findByBuyercdAndItemcdAndUseyn(@NotNull String buyercd, @NotNull String itemcd, String useyn);

    @Query("SELECT ip FROM M_ITEM_PRICE ip " +
            "INNER JOIN M_ITEM i ON ip.itemcd = i.itemcd " +
            "INNER JOIN M_BUYER b ON ip.buyercd  = b.buyercd " +
            "WHERE (:item IS NULL OR :item = '' OR LOWER(ip.itemcd) LIKE LOWER(CONCAT('%', :item, '%')) OR LOWER(i.itemnm) LIKE LOWER(CONCAT('%', :item, '%'))) " +
            "AND (:buyer IS NULL OR :buyer = '' OR LOWER(ip.buyercd) LIKE LOWER(CONCAT('%', :buyer, '%')) OR LOWER(b.buyernm) LIKE LOWER(CONCAT('%', :buyer, '%'))) " +
            "AND ip.useyn = 'Y'")
            List<ItemPrice> searchItemPrice(@Param("item") String item, @Param("buyer") String buyer);

}
