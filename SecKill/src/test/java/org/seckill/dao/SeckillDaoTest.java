package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.redis.UserDao;
import org.seckill.entity.Seckill;
import org.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/16.
 */
//加载IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
//@ContextConfiguration({"classpath:spring/spring-dao.xml"})
@ContextConfiguration(locations = {"classpath*:spring/redis-spring.xml"})
public class SeckillDaoTest {
    @Resource
    private UserDao userDao;
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void queryById() throws Exception {
//        long seckillId=1000;
//        Seckill seckill=seckillDao.queryById(seckillId);
//        System.out.println(seckill.getName());
//        System.out.println(seckill);
        User user = new User();
        user.setId(2);
        user.setName("rona");
        userDao.saveUser(user);
    }

    @Test
    public void queryAll() throws Exception {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println("A=" + a +",b="+b + ",sum=" +sum);

    }

    @Test
    public void reduceNumber() throws Exception {
        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        jedis.lpush("player", "paul");
//        jedis.lpush("player", "wade");
//        jedis.lpush("player", "anthony");
//        jedis.lpush("player", "james");
//        List<String> list = jedis.lrange("player", 0, 5);
//        for (String str : list) {
//            System.out.println("player:" + str);
//        }
        String str = "abc";
        System.out.println(str.substring(str.length() - 2, str.length()-1));
    }
}