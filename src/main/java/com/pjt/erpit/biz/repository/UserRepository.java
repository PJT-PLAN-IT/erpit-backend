package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.User;
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