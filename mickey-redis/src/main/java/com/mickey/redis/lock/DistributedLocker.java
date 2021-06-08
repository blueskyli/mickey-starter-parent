package com.mickey.redis.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author J·K
 * @Description: TODO
 * @date 2019-06-27 14:50
 */
public interface DistributedLocker {
    RLock lock(String lockKey);
    RLock lock(String lockKey, int timeout);
    RLock lock(String lockKey, TimeUnit unit, int timeout);
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);
    void unlock(String lockKey);
    void unlock(RLock lock);
}
