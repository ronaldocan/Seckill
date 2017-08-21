package org.seckill.dto;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Exposer {
    //是否暴露
    private boolean exposed;
    //现在时间毫秒数
    private long now;
    //结束时间毫秒数
    private long end;
    //创建时间毫秒数
    private long start;

    private long seckillId;

    private String md5Url;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Exposer(boolean exposed, long seckillId, long now, long end, long start) {
        this.exposed = exposed;
        this.now = now;
        this.end = end;
        this.start = start;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, String md5Url) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.md5Url = md5Url;
    }

    public Exposer(boolean exposed, long now, long end, long start) {
        this.exposed = exposed;
        this.now = now;
        this.end = end;
        this.start = start;
    }

    public Exposer(boolean exposed,String desc ,long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.desc = desc;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5Url() {
        return md5Url;
    }

    public void setMd5Url(String md5Url) {
        this.md5Url = md5Url;
    }
}

