package com.luch.repository.sharding;

import com.luch.RunBoot;
import com.luch.entity.City;
import com.luch.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luch
 * @date 2021/11/16 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestCityRepository {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testAdd() {
        for (int i = 0; i < 20; i++) {
            City city = new City();
            city.setName("上海" + i);
            city.setProvince("上海");
            cityRepository.save(city);
        }
    }
}
