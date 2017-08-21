package org.seckill.dto;

import org.seckill.common.SeckillEnum;
import org.seckill.entity.SuccessKilled;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SeckillExecution {
    //秒杀状态
    private int state;

    private int userPhone;

    private long seckillId;

    private String stateInfo;

    private SeckillEnum seckillEnum;

    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, String stateInfo) {
        this.seckillId = seckillId;
        this.stateInfo = stateInfo;
    }

    public SeckillExecution(long seckillId, SeckillEnum seckillEnum, SuccessKilled successKilled) {

        this.seckillId = seckillId;
        this.seckillEnum = seckillEnum;
        this.successKilled = successKilled;
    }

    public SeckillEnum getSeckillEnum() {

        return seckillEnum;
    }

    public void setSeckillEnum(SeckillEnum seckillEnum) {
        this.seckillEnum = seckillEnum;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    public SeckillExecution(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public SeckillExecution(long seckillId) {

        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillExecution(int userPhone, long seckillId,SeckillEnum seckillEnum) {
        this.userPhone = userPhone;
        this.seckillId = seckillId;
        this.seckillEnum = seckillEnum;
    }

    public SeckillExecution(long seckillId, SeckillEnum seckillEnum) {
        this.seckillId = seckillId;
        this.seckillEnum = seckillEnum;
    }
}
