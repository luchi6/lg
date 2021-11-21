package com.luch.hint;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author luch
 * @date 2021/11/21 11:59
 */
public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, // 相当于ds0,ds1的集合
                                         HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : collection) {
            for (Long value : hintShardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
