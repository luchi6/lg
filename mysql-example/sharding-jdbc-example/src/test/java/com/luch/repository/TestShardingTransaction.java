package com.luch.repository;

import com.luch.RunBoot;
import com.luch.entity.Position;
import com.luch.entity.PositionDetail;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author luch
 * @date 2021/11/21 17:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestShardingTransaction {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionDetailRepository positionDetailRepository;

    @Test
    @Transactional
//    @ShardingTransactionType(TransactionType.XA)
    @ShardingTransactionType(TransactionType.BASE)
    public void test1() {
//        TransactionTypeHolder.set(TransactionType.XA);
        for (int i = 0; i < 3; i++) {
            Position position = new Position();
            position.setName("root" + i);
            position.setSalary("10000");
            position.setCity("shanghai");
            positionRepository.save(position);

            if (i == 2) {
                throw new RuntimeException("test");
            }

            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setId(position.getId());
            positionDetail.setDescription("this is a root " + i);
            positionDetailRepository.save(positionDetail);
        }
    }
}
