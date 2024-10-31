package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.dto.item.SearchItemDTO;
import com.pjt.erpit.biz.entity.Item;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Item Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Boolean existsByItemcd(String itemcd);

    Item findByItemcd(String itemcd);

    @Query("SELECT i FROM M_ITEM  i WHERE LOWER(i.itemcd) LIKE LOWER(CONCAT('%', :item, '%')) " +
            "OR LOWER(i.itemnm) LIKE LOWER(CONCAT('%', :item, '%'))")
    List<Item> findByItemcdOrItemnm(@Param("item") String item);

    @Query("SELECT new com.pjt.erpit.biz.dto.item.SearchItemDTO(i.itemnm, i.originprice, i.unit, i.stock) FROM M_ITEM i WHERE i.itemcd = :itemcd")
    SearchItemDTO searchItem(@Param("itemcd") String itemcd);

    @Query("SELECT i.itemnm FROM M_ITEM i WHERE i.itemcd = :itemcd")
    String findByItemnm(@Param("itemcd") String itemcd);

    Item findByItemid(@NotNull Long itemId);
}
