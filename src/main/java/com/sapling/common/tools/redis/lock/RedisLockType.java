package com.sapling.common.tools.redis.lock;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
public enum  RedisLockType {

    SETNX_EXPIRE,

    SETNX_EXPIRE_LUA,

    REDISSON,

    REDLOCK;

}
