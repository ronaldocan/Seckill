package org.seckill.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SuccessKilled {
    private long seckilled_id;
    private int user_phone;
    private int state;
    private Date create_time;
    private Seckill seckill;

    public long getSeckilled_id() {
        return seckilled_id;
    }

    @Override

    public String toString() {
        return "SuccessKilled{" +
                "seckilled_id=" + seckilled_id +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", create_time=" + create_time +
                ", seckill=" + seckill +
                '}';
    }


    public void setSeckilled_id(long seckilled_id) {
        this.seckilled_id = seckilled_id;
    }

    public int getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(int user_phone) {
        this.user_phone = user_phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
}
