package com.sapling.common.tools.redis.limit;

import com.sapling.common.tools.redis.RedisConstants;
import com.sapling.common.tools.redis.RedisTools;
import redis.clients.jedis.Jedis;

/**
 * 此类试用于滚动调用频率控制，不适用于固定区间调用的控制
 *
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
     * @param id             身份，可以是ip，也可以是appid等识别身份的标识，标识某个标识在某个接口上一段时间内只能调用的次数限制
     * @param apiName        接口名称
     * @param periodInMillis 时间间隔--毫秒
     * @param count          次数限制
     * @return boolean: 超过限制 - true, 未超过限制 - false
     */
    public static boolean checkIsLimited(Jedis jedis, String id, String apiName, Long periodInMillis, int count) {
        String key = RedisTools.genRedisKey(RedisConstants.REDIS_SEPERATOR, REDIS_PRIFIX, id, apiName);
        return checkIsLimited(jedis, key, periodInMillis, count);
    }

    /**
     * 检查是否超限
     *
     * @param jedis
     * @param key
     * @param periodInMillis
     * @param count
     * @return
     */
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
     * @param id      身份，可以是ip，也可以是appid等识别身份的标识，标识某个标识在某个接口上一段时间内只能调用的次数限制
     * @param apiName 接口地址
     * @param time    时间戳
     * @return 移除的记录数
     */
    public static long removeRecordsBeforeTime(Jedis jedis, String id, String apiName, Long time) {
        String key = RedisTools.genRedisKey(RedisConstants.REDIS_SEPERATOR, REDIS_PRIFIX, id, apiName);
        return removeRecordsBeforeTime(jedis, key, time);
    }

    /**
     * 删除从开始到某个时间段内的值
     *
     * @param jedis
     * @param key
     * @param time
     * @return
     */
    public static long removeRecordsBeforeTime(Jedis jedis, String key, Long time) {
        return jedis.zremrangeByScore(key, 0, time);
    }
}
