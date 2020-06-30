package com.sapling.common.tools.redis.lock;

import java.lang.annotation.Annotation;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
public class RedisLockTestBase extends RedisTestBase {

    public static RedisLock redisLock(){
        RedisLock redisLock = new RedisLock(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return RedisLock.class;
            }

            @Override
            public RedisLockType type() {
                return null;
            }

            @Override
            public int expire() {
                return 1;
            }

            @Override
            public int waitTime() {
                return 3;
            }

            @Override
            public int forceUnLockTime() {
                return 10;
            }
        };
        return redisLock;
    }
}
