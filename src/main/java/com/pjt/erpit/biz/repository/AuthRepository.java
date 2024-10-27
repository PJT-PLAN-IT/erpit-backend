package com.pjt.erpit.biz.repository;

import com.pjt.erpit.biz.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Auth Repository
 */
@SuppressWarnings("SpellCheckingInspection")
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByUsercd(String usercd);
}
