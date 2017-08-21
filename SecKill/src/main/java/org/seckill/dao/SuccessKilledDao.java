package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface SuccessKilledDao {
    /**
     *
     * @param seckillId
     * @param phone
     * @return
     */
    int insertSuccessKilled(long seckillId, int phone);

    SuccessKilled queryByScekillIdAndPhone(long seckillId, int phone);

    List<SuccessKilled> queryByIdWithSeckilled(long seckillId);

}
