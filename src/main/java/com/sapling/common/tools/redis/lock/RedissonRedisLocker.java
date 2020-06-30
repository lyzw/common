package com.sapling.common.tools.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/29
 * @since 1.0
 */
public class RedissonRedisLocker implements RedisLocker{

    @Override
    public boolean tryLock(Jedis jedis, String key, String value, RedisLock redisLock) {
        return false;
    }

    @Override
    public RedisLockType type() {
        return RedisLockType.REDISSON;
    }
}
