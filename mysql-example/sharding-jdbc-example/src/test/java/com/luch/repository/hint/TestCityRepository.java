package com.luch.repository.hint;

import com.luch.RunBoot;
import com.luch.entity.City;
import com.luch.repository.CityRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luch
 * @date 2021/11/21 12:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestCityRepository {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void test1() {
        HintManager hintManager =  HintManager.getInstance();
        // 只路由数据库
        hintManager.setDatabaseShardingValue(1L);
        int size = cityRepository.findAll().size();
        System.out.println("size = " + size);
    }

    @Test
    public void test2() {
        HintManager hintManager =  HintManager.getInstance();
        hintManager.addDatabaseShardingValue("city", 1L);
        hintManager.addTableShardingValue("city", 0L);
        City city = new City();
        city.setName("南阳" );
        city.setProvince("河南");
        cityRepository.save(city);
    }
}
