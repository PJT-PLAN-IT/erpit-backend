package com.example.bot.biz.repository;

import com.example.bot.biz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 */
@SuppressWarnings("SpellCheckingInspection")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsercd(String usercd);

    User findByUsercd(String usercd);
}