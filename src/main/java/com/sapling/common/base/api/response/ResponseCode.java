package com.sapling.common.base.api.response;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/11/12
 * @since 1.0
 */
public enum ResponseCode {

    /**
     * success
     */
    SUCCESS("000000","SUCCESS"),

    /**
     * permission deny
     */
    PERMISSION_DENY("999998","Permission deny!Pls check your permission!"),

    /**
     * system error
     */
    SYSTEM_ERROR("999999","System error! Pls contact to system manager!"),

    ;

    String code;

    String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
