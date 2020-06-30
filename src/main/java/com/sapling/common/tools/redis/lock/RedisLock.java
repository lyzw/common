package com.sapling.common.tools.redis.lock;

import java.lang.annotation.*;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLock {

    /**
     * redis锁的类别
     * @return
     */
    RedisLockType type() default RedisLockType.SETNX_EXPIRE;

    /**
     * 锁的超时时间,单位毫秒
     * @return
     */
    int expire() default 500;

    /**
     * 锁的最大等待时间,单位毫秒
     * @return
     */
    int waitTime() default 2000;

    /**
     * 锁的强制退出时间,单位毫秒
     * @return
     */
    int forceUnLockTime() default 10000;
}
