package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);
    /***
     *
     *
     * */
    SeckillExecution executeSeckill(long seckillId, String md5Url, int userPhone)throws
            RepeatKillException,SeckillException,SeckillCloseException;
    /**
     * */
    SeckillExecution executeSeckillByProcedure(long seckillId, String md5Url, int userPhone)throws
            RepeatKillException,SeckillException,SeckillCloseException;



}
