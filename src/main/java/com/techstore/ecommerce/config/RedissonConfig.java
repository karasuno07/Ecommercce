package com.techstore.ecommerce.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedissonConfig {

    @Value("${spring.redis.client-name:master}")
    private String clientName;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.url:redis://127.0.0.1:6379}")
    private String uri;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setClientName("master")
                .setPassword(password)
                .setAddress(uri)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(5)
                .setConnectTimeout(30000);;

        return Redisson.create(config);
    }

    @Bean
    public RedissonConnectionFactory
    redissonConnectionFactory(RedissonClient client) {
        return new RedissonConnectionFactory(client);
    }
}
