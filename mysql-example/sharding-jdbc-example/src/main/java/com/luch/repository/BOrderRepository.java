package com.luch.repository;

import com.luch.entity.BOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luch
 * @description position
 * @date 2021/11/16 0:17
 */
public interface BOrderRepository extends JpaRepository<BOrder,Long> {
}
