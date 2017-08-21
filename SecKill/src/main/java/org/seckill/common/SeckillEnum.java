package org.seckill.common;

/**
 * Created by Administrator on 2017/5/17.
 */
public enum SeckillEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEATE_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3, "数据篡改");


    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private int state;
    private String stateInfo;

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    SeckillEnum(int state, String stateInfo) {

        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static String stateOf(int state) {
        switch (state){
            case 1:
                return SUCCESS.getStateInfo();
            case 0:
                return END.getStateInfo();
            case -1:
                return REPEATE_KILL.getStateInfo();
            case -2:
                return INNER_ERROR.getStateInfo();
            case -3:
                return DATA_REWRITE.getStateInfo();
            default:
                break;
        }
        return null;
    }
}
