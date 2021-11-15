package com.luch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author luch
 * @date 2021/11/13 21:11
 */

@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {
    private int port;
    private String path;
}
