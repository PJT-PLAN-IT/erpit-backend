package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Item Repository
 */
@SuppressWarnings({"SpellCheckingInspection"})
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Boolean existsByItemcd(String itemcd);
}
