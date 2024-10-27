package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.history.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Item History Repository
 */
@Repository
public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {
}
