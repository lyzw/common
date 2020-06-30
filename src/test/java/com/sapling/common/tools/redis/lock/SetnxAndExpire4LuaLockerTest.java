package com.sapling.common.tools.redis.lock;

import org.junit.Test;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/27
 * @since 1.0
 */
public class SetnxAndExpire4LuaLockerTest extends RedisLockTestBase {

    @Test
    public void tryLock() {
        new SetnxAndExpireWithLuaLocker().tryLock(createJedis(),"lua","1",redisLock());
    }

    @Test
    public void unlock() {
    }

    @Test
    public void type() {
    }
}