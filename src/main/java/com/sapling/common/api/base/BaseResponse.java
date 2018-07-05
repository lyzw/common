package com.sapling.common.api.base;

import java.io.Serializable;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class BaseResponse implements Serializable {

    private String retCode;

    private String retMsg;

    private Object Data;

    private Long responseTime = System.currentTimeMillis();

    private String requestId;

    private String responseId;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
