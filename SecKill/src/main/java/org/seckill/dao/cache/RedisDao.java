package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017/5/24.
 */
public class RedisDao {
    private final JedisPool jedisPool;
    private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public RedisDao(String host,int port){
        jedisPool = new JedisPool(host, port);
    }

    /***
     *  从缓存中查找秒杀商品
     * @param seckillId 秒杀商品Id
     * @return
     */
    public Seckill getSeckill(long seckillId)
    {
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckillId:" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Seckill seckill = schema.newMessage();
                    //将bytes反序列化
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    return seckill;
                }

            }finally {
                jedis.close();
            }
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /***
     * 将秒杀商品存在缓存中
     * @param seckill 秒杀商品ID
     * @return
     */
    public String setSeckill(Seckill seckill)
    {
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckillId:" + seckill.getSeckill_id();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
