package com.luch.repository;

import com.luch.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luch
 * @description position
 * @date 2021/11/16 0:17
 */
public interface PositionRepository extends JpaRepository<Position,Long> {
}
