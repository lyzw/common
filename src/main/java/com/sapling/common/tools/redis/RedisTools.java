package com.sapling.common.tools.redis;


/**
 * @author zhouwei
 * @version v1.0
 * @createon 2018年06月06日
 * @since v1.0
 */
public class RedisTools {

    /**
     * @param keySeperator
     * @param keys
     * @return
     */
    public static String genRedisKey(String keySeperator, String... keys) {
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("keys is null");
        }
        if (keys.length == 1) {
            return keys[0];
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (String s : keys) {
            sb.append(s);
            if (index != keys.length) {
                sb.append(keySeperator);
            }
            index++;
        }
        return sb.toString();
    }
}
