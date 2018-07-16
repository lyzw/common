package com.sapling.common.tools.collection;

import java.util.List;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/6/25
 * @since v1.0
 */
public class ListUtil {

    public static boolean compare(List<Object> one, List<Object> other) {
        if (one == null && other == null) {
            return true;
        } else if (one == null || other == null) {
            return false;
        }
        if (one.size() != other.size()) {
            return false;
        }
        boolean flag = true;
        for (Object o : one) {
            if (other.contains(o)) {
                continue;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
