package com.sapling.common.tools.redis.lock;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
@Slf4j
public class SetnxAndExpireLocker implements RedisLocker  {


    @Override
    public boolean tryLock(Jedis jedis, String key, String value, RedisLock redisLock) {
        RedisLockValue lockValue = RedisLockValue.currentValue(value);
        // 尝试获取锁
        Long result = jedis.setnx(key, JSON.toJSONString(lockValue));
        if (result != null && result == 1L) {
            // 如果设置超时成功，则返回成功，否则，释放锁并返回失败
            try {
                jedis.expire(key, redisLock.expire()/1000);
            }catch (Exception e){
                jedis.del(key);
                return false;
            }
            log.debug("set lock successful!");
            return true;
        } else {
            try {
                // 设置可重入
                RedisLockValue existsValue = getRedisLockValue(jedis,key);
                if (existsValue.isValueEquals(value)){
                    log.debug("the value is same, get lock again!");
                    return true;
                }
                // 为了防止设置expire失败导致死锁
                //超时，则强制解锁，并重新获取锁
                if (isAboveForceLockTime(jedis, key, lockValue, redisLock.forceUnLockTime())) {
                    log.debug("the lock time is above the force unlock time, force unlock and try lock again!");
                    jedis.del(key);
                    return tryLock(jedis, key, value, redisLock);
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }


    @Override
    public RedisLockType type() {
        return RedisLockType.SETNX_EXPIRE;
    }

}
