package org.seckill.exception;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SeckillCloseException extends RuntimeException {
    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(Throwable cause) {

        super(cause);
    }

    public SeckillCloseException(String message) {
        super(message);
    }
}
