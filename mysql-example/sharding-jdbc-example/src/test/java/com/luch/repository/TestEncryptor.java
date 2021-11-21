package com.luch.repository;

import com.luch.RunBoot;
import com.luch.entity.CUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luch
 * @date 2021/11/21 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestEncryptor {
    @Autowired
    private CUserRepository cUserRepository;

    @Test
    public void test1() {
        CUser cUser = new CUser();
        cUser.setName("luch");
        cUser.setPwd("123");
        cUserRepository.save(cUser);
    }
}
