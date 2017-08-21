package org.Thread;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/8/6.
 */
public class Cache {
    static HashMap<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();
    static boolean update = false;
    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }
    // 设置key对应的value，并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }
    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public static final void processData() {
        r.lock();
        if (!update) {
            r.unlock();
            w.lock();
            try{
                if(!update)
                    //准备数据的流程
                    update = true;
                r.lock();
            }finally {
                w.unlock();
            }
        }
        try {
            // 使用数据的流程（略）
        } finally {
            r.unlock();
        }
    }
}
