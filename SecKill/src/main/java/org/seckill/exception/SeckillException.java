package org.seckill.exception;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(Throwable cause) {
        super(cause);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }
}
