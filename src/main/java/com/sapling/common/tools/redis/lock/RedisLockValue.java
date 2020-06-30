package com.sapling.common.tools.redis.lock;

import com.sapling.common.tools.common.StringUtil;
import lombok.Data;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
@Data
public class RedisLockValue {

    private String value;

    private long time;

    public static RedisLockValue currentValue(String value) {
        RedisLockValue lockValue = new RedisLockValue();
        lockValue.setTime(System.currentTimeMillis());
        lockValue.setValue(value);
        return lockValue;
    }

    /**
     * 判断值是否相等
     *
     * @param lockValue
     * @return
     */
    public boolean isValueEquals(RedisLockValue lockValue) {
        if (lockValue == null) {
            return false;
        }
        if (this.value.equals(lockValue.getValue())) {
            return true;
        }
        return false;
    }
    /**
     * 判断值是否相等
     *
     * @param lockValue
     * @return
     */
    public boolean isValueEquals(String lockValue) {
        if (StringUtil.isEmpty(lockValue)) {
            return false;
        }
        if (this.value.equals(lockValue.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断值是否相等
     *
     * @param value1
     * @param value2
     * @return
     */
    public boolean isValueEquals(RedisLockValue value1, RedisLockValue value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.getValue() == null && value2.getValue() == null) {
            return true;
        }
        if (value1.getValue() == null || value2.getValue() == null) {
            return false;
        }
        if (value1.getValue().equals(value2.getValue())) {
            return true;
        }
        return false;
    }

}
