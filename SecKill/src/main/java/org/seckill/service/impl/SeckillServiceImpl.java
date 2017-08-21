package org.seckill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.seckill.common.SeckillEnum;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service("seckillServiceImpl")
public class SeckillServiceImpl implements SeckillService{
    private static Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
    private static String suffix = "rona";
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    private RedisDao redisDao;
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    public Seckill getById(long seckillId) {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill != null)
            return seckill;
        Seckill temp = seckillDao.queryById(seckillId);
        redisDao.setSeckill(temp);
        return temp;
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = getById(seckillId);
        Date startTime = seckill.getStart_time();
        Date endTime = seckill.getEnd_time();
        Date nowTime = new Date();
        if(seckill != null){
            if (nowTime.getTime() >= startTime.getTime() && nowTime.getTime() <= endTime.getTime()) {
                String md5Url = getMd5(seckillId);
                return new Exposer(true, seckillId, md5Url);
            }
            else
            {
                return new Exposer(true, seckillId, startTime.getTime(), endTime.getTime(), seckill.getCreate_time().getTime());
            }
        }
        return new Exposer(false,"商品不存在",seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + suffix;
        //Spring提供的工具包
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, String md5Url, int userPhone) throws RepeatKillException, SeckillException, SeckillCloseException {
        if (md5Url == null || !md5Url.equals(getMd5(seckillId))) {
            return new SeckillExecution(seckillId,SeckillEnum.DATA_REWRITE);
        }
        Date nowTime = new Date();
        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            if (insertCount == 0) {
                throw new RepeatKillException("You're repeatKill");
            }
            else {
                int resultCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (resultCount <= 0) {
                    throw new SeckillCloseException("Product is stop saling");
                }else
                {
                    return new SeckillExecution(userPhone, seckillId, SeckillEnum.SUCCESS);
                }
           }

        }
        catch (SeckillCloseException e) {
            throw new SeckillCloseException(e.getMessage());

        }
        catch (RepeatKillException e) {
            throw new RepeatKillException(e.getMessage());

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner eroor " + e.getMessage());

        }

    }

    public SeckillExecution executeSeckillByProcedure(long seckillId, String md5Url, int userPhone) throws RepeatKillException, SeckillException, SeckillCloseException {
        if (md5Url == null || !md5Url.equals(getMd5(seckillId))) {
            throw new SeckillException("data need rewrite");
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            seckillDao.executeByProcedure(map);
            Integer result = MapUtils.getInteger(map,"result");
            if (result > 0) {
                SuccessKilled successKilled = successKilledDao.queryByScekillIdAndPhone(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillEnum.SUCCESS,successKilled);
            }else {
                return new SeckillExecution(seckillId,SeckillEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillEnum.INNER_ERROR);
        }

    }
}
