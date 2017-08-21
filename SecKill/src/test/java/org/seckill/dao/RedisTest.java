package org.seckill.dao;

import org.seckill.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class RedisTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/redis-spring.xml");
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(10);
//        jedisPoolConfig.setMaxTotal(50);
//        jedisPoolConfig.setTestOnBorrow(false);
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
//        jedisConnectionFactory.setHostName("127.0.0.1");
//        jedisConnectionFactory.setPort(6379);
//        final RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) applicationContext.getBean("redisTemplate");
////        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        final User user = new User();
//        user.setName("rona");
//        user.setId(11);
//        redisTemplate.execute(new RedisCallback() {
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.set(redisTemplate.getStringSerializer().serialize(String.valueOf(user.getId())),
//                        redisTemplate.getStringSerializer().serialize(user.getName())
//                );
//                return null;
//            }
//        });
//        User user2 = getUser(redisTemplate,user.getId());
//        System.out.println(user2);
        System.out.println(1 << 3);
    }


    public static User getUser(final RedisTemplate redisTemplate,final long id)
    {

        User user = (User) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] bytes = redisTemplate.getStringSerializer().serialize(String.valueOf(id));
                if(redisConnection.exists(bytes))
                {
                    byte[] nameBytes = redisConnection.get(bytes);
                    User user = new User();
                    user.setName((String) redisTemplate.getStringSerializer().deserialize(nameBytes));
                    user.setId(id);
                    return user;
                }
                return null;
            }
        });
        return user;
    }
}
