package com.luch.repository;

import com.luch.entity.CUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luch
 * @date 2021/11/21 15:15
 */
public interface CUserRepository extends JpaRepository<CUser, Long> {
}
