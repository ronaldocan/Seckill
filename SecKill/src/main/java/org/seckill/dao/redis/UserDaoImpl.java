package org.seckill.dao.redis;

import org.seckill.dao.redis.UserDao;
import org.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/3.
 */
@Repository("IuserDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private RedisTemplate redisTemplate;
    public void saveUser(final User user)
    {
        redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(redisTemplate.getStringSerializer().serialize(user.getId()),
                        redisTemplate.getStringSerializer().serialize(user.getName())
                        );
                return null;
            }
        });
    }
    public User getUser(final int id)
    {
        redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] bytes = redisTemplate.getStringSerializer().serialize(id);
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
        return null;
    }
}
