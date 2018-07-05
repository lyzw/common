package com.sapling.common.api.base;

import java.io.Serializable;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class BaseRequest implements Serializable {

    private static final Long serialVersionUID = 1l;

    private Long requestTime;

    private String requestId;

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
