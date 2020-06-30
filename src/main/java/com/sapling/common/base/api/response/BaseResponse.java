package com.sapling.common.base.api.response;

import com.sapling.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
@Data
@EqualsAndHashCode
public class BaseResponse<T> extends BaseEntity {

    private String retCode;

    private String retMsg;

    private T data;

    private Long responseTime = System.currentTimeMillis();

    private String requestId;

    private String responseId;



}
