package org.seckill.dao.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface RedisDataSource {
    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
