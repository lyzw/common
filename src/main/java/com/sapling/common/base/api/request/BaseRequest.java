package com.sapling.common.base.api.request;

import com.sapling.common.base.BaseEntity;
import lombok.Data;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
@Data
public class BaseRequest<T> extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    private Long requestTime;

    private String requestId;

    private T data;

}
