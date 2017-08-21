package org.seckill.exception;

/**
 * Created by Administrator on 2017/5/17.
 */
public class RepeatKillException extends RuntimeException {
    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(String message) {
        super(message);
    }
}
