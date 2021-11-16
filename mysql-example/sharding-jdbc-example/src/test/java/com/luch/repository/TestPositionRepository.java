package com.luch.repository;

import com.luch.RunBoot;
import com.luch.entity.Position;
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
public class TestPositionRepository {
    @Autowired
    private PositionRepository positionRepository;

    @Test
    public void testAdd() {
        for (int i = 0; i < 20; i++) {
            Position position = new Position();
//            position.setId(i);
            position.setName("luch" + i);
            position.setSalary("10000");
            position.setCity("shanghai");
            positionRepository.save(position);
        }
    }
}
