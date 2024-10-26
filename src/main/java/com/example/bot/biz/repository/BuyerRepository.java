package com.example.bot.biz.repository;

import com.example.bot.biz.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Buyer Repository
 */
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
