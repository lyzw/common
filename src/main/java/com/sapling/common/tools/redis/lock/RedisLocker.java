package com.sapling.common.tools.redis.lock;

import com.alibaba.fastjson.JSON;
import com.sapling.common.tools.common.StringUtil;
import redis.clients.jedis.Jedis;

/**
 * redis分布式锁的处理类
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
public interface RedisLocker {

    /**
     * 尝试加锁
     *
     * @param jedis     redis链接
     * @param key       redis锁的key值
     * @param value     redis锁的值
     * @param redisLock redis锁的配置，从注解中获取，也可以手动配置
     * @return true - 加锁成功，包括重入，false - 加锁失败
     */
    boolean tryLock(Jedis jedis, String key, String value, RedisLock redisLock);

    /**
     * 解锁
     *
     * @param jedis redis链接
     * @param key   redis锁的key值
     * @param value redis锁的值
     * @return 是否已经完成解锁
     */
    default boolean unlock(Jedis jedis, String key, String value) {
        try {
            if (isReentrant(jedis, key, value)) {
                jedis.del(key);
                return true;
            }
        } catch (Exception e) {
            jedis.del(key);
            return true;
        }
        return false;
    }

    RedisLockType type();


    /**
     * 判断是否可重入
     *
     * @param jedis redis链接
     * @param key   redis锁的key值
     * @param value redis锁的值
     * @return 是否可以重入，true - 可以，false - 不可以
     */
    default boolean isReentrant(Jedis jedis, String key, String value) {
        RedisLockValue lockValue = this.getRedisLockValue(jedis, key);
        return lockValue == null || lockValue.isValueEquals(value);
    }

    /**
     * 判断是否超过了指定的时间
     *
     * @param jedis           redis链接
     * @param key             redis锁的key值
     * @param lockValue       redis锁的值
     * @param forceUnlockTime 强制解锁的时间
     * @return 是否已经超过了强制解锁的时间
     */
    default boolean isAboveForceLockTime(Jedis jedis, String key,
                                         RedisLockValue lockValue,
                                         int forceUnlockTime) {
        try {
            RedisLockValue existsLockValue = getRedisLockValue(jedis, key);
            return isAboveForceLockTime(lockValue, existsLockValue, forceUnlockTime);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否超过了指定的时间
     *
     * @param lockValue       redis锁的值
     * @param existsLockValue redis中已经存在的锁的值
     * @param forceUnlockTime 强制解锁的时间
     * @return 数据库
     */
    default boolean isAboveForceLockTime(RedisLockValue lockValue,
                                         RedisLockValue existsLockValue,
                                         int forceUnlockTime) {
        return (lockValue.getTime() - existsLockValue.getTime() - forceUnlockTime) > 0;
    }

    default RedisLockValue getRedisLockValue(Jedis jedis, String key) {
        String existsValue = jedis.get(key);
        if (StringUtil.isEmpty(existsValue)) {
            return null;
        }
        return JSON.parseObject(existsValue, RedisLockValue.class);
    }
}
