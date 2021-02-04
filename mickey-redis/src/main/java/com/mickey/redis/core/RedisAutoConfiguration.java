package com.mickey.redis.core;

import com.mickey.redis.config.JkRedisAutoConfiguration;
import com.mickey.redis.lock.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * @author J·K
 * @Description: redis 自动注入
 * @date 2018/7/30 15:09
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import({  JkRedisAutoConfiguration.class, RedissonAutoConfiguration.class})
@Order(0)
public class RedisAutoConfiguration
{
}
