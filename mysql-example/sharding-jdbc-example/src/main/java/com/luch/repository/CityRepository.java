package com.luch.repository;

import com.luch.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luch
 * @description position
 * @date 2021/11/16 0:17
 */
public interface CityRepository extends JpaRepository<City,Long> {
}
