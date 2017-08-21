package org.seckill.sprd;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by Administrator on 2017/8/3.
 */
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        // Defaults
        redisConnectionFactory.setHostName("192.168.1.166");
        redisConnectionFactory.setPort(6379);
        return redisConnectionFactory;
    }
}
