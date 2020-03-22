package com.mickey.redis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author J·K
 * @Description: TODO
 * @date 2019-06-27 14:50
 */
public class RedissonDistributedLocker implements DistributedLocker {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 没有超时时间,默认30s
     * @param lockKey
     * @return
     */
    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 自己设置超时时间
     * @param lockKey 锁的key
     * @param timeout  秒  如果是-1，直到自己解锁，否则不会自动解锁
     * @return
     */
    @Override
    public RLock lock(String lockKey, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }

    @Override
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout,unit);
        return lock;
    }

    /**
     *
     * @param lockKey  锁key
     * @param unit  锁单位
     * @param waitTime   等到最大时间，强制获取锁
     * @param leaseTime  锁失效时间
     * @return
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime,leaseTime,unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }
}
