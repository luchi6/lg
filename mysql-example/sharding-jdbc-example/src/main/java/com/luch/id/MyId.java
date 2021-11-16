package com.luch.id;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.aspectj.weaver.ast.Var;

import java.util.Properties;

/**
 * @author luch
 * @date 2021/11/16 23:04
 */
public class MyId implements ShardingKeyGenerator {
    private SnowflakeShardingKeyGenerator snow =new SnowflakeShardingKeyGenerator();

    @Override
    public Comparable<?> generateKey() {
        return snow.generateKey();
    }

    @Override
    public String getType() {
        return "Luch";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
