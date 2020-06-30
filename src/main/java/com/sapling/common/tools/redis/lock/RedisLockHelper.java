package com.sapling.common.tools.redis.lock;

import com.sapling.common.tools.security.MD5Util;
import com.sapling.common.tools.system.NetworkInfoUtil;
import com.sapling.common.tools.system.SystemInfoUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
@Slf4j
public class RedisLockHelper {

    private static String valuePattern = "%s-%s-%s";

    /**
     * 获取Redis 锁的值，此值根据IP+HOSTNAME+线程信息组成
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String getRedisLockValue() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Thread thread = Thread.currentThread();
        String hostName = "";
        try{
            hostName = NetworkInfoUtil.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return MD5Util.md5(String.format(valuePattern,NetworkInfoUtil.getHostIp(),hostName,thread.toString()));
    }


}
