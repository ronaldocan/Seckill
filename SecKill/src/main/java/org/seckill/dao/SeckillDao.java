package org.seckill.dao;

import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface SeckillDao {

    /**
     * 根据ID找秒杀商品
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offset, int limit);
    /**
     * 减少库存
     * @param seckillId
     * @param
     * @return
     */
    int reduceNumber(long seckillId,Date killTime);

    void executeByProcedure(Map<String,Object> map);

}
