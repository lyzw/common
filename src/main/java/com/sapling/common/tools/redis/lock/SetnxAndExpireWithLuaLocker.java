package com.sapling.common.tools.redis.lock;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

import static com.sapling.common.tools.redis.lock.RedisLockType.SETNX_EXPIRE_LUA;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/27
 * @since 1.0
 */
public class SetnxAndExpireWithLuaLocker implements RedisLocker  {

    @Override
    public boolean tryLock(Jedis jedis, String key, String value, RedisLock redisLock) {
        String luaScripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then " +
                " redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(value);
        values.add(String.valueOf(redisLock.expire()));
        Object result = jedis.eval(luaScripts, keys, values);
        //判断是否成功
        if (result.equals(1L)){
            return true;
        }
        // 判断是否可重入
        return isReentrant(jedis, key, value);
    }

    @Override
    public RedisLockType type() {
        return SETNX_EXPIRE_LUA;
    }


}
