package com.sapling.common.tools.redis;

import redis.clients.jedis.Jedis;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018-06-06
 * @since v1.0
 */
public class FrequentCallerChecker {

    public static String REDIS_PRIFIX;


    /**
     * 检测某个api接口在某段时间内是否超过特定次数的限制
     *
     * @param jedis          redis连接（方法不负责redis连接的创建与销毁）
     * @param ip             IP地址
     * @param apiName        接口名称
     * @param periodInMillis 时间间隔--毫秒
     * @param count          次数限制
     * @return boolean: 超过限制 - true, 未超过限制 - false
     */
    public static boolean checkIsLimited(Jedis jedis, String ip, String apiName, Long periodInMillis, int count) {
        String key = RedisTools.genRedisKey(RedisConstants.REDIS_SEPERATOR, REDIS_PRIFIX, ip, apiName);
        return checkIsLimited(jedis, key, periodInMillis, count);
    }

    public static boolean checkIsLimited(Jedis jedis, String key, Long periodInMillis, int count) {
        Long current = System.currentTimeMillis();
        Long leastTime = current - periodInMillis;
        Long zcount = jedis.zcount(key, leastTime, current);
        if (zcount > count) {
            return true;
        }
        jedis.zadd(key, System.currentTimeMillis(), String.valueOf(System.currentTimeMillis()));
        return false;
    }

    /**
     * 移除给定时间以前的访问记录
     *
     * @param jedis   redis连接（方法不负责redis连接的创建与销毁）
     * @param ip      ip地址
     * @param apiName 接口地址
     * @param time    时间戳
     * @return 移除的记录数
     */
    public static long removeRecordsBeforeTime(Jedis jedis, String ip, String apiName, Long time) {
        String key = RedisTools.genRedisKey(RedisConstants.REDIS_SEPERATOR, REDIS_PRIFIX, ip, apiName);
        return removeRecordsBeforeTime(jedis, key, time);
    }

    public static long removeRecordsBeforeTime(Jedis jedis, String key, Long time) {
        return jedis.zremrangeByScore(key, 0, time);
    }
}
