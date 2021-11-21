package com.luch;

import com.luch.entity.Position;
import com.luch.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author luch
 * @date 2021/11/21 21:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestPosition {
    @Autowired
    private PositionRepository positionRepository;

    @Test
    public void test1() {
        List<Position> list = positionRepository.findAll();

//        list.forEach(System.out::println);
    }
}
