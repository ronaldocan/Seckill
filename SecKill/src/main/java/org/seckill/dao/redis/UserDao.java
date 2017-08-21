package org.seckill.dao.redis;

import org.seckill.entity.User;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface UserDao {
    void saveUser(final User user);
    User getUser(final int id);
}
