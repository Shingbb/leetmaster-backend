package com.shing.leetmaster.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置
 *
 * @author Shing
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    // Redis客户端的主机地址
    private String host;

    // Redis客户端的端口号
    private Integer port;

    // Redis数据库的编号
    private Integer database;

    // Redis数据库的密码
    private String password;

    /**
     * 创建并配置Redisson客户端的Bean
     *
     * @return RedissonClient实例，用于与Redis数据库进行交互
     */
    @Bean
    public RedissonClient redissonClient() {
        // 创建并配置Redis连接的Config对象
        Config config = new Config();
        config.useSingleServer()
                // 设置Redis服务器的地址和端口
                .setAddress("redis://" + host + ":" + port)
                // 设置要使用的数据库编号
                .setDatabase(database)
                // 设置数据库的密码
                .setPassword(password);
        // 使用配置创建并返回RedissonClient实例
        return Redisson.create(config);
    }
}

