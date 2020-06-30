package com.sapling.common.base.api.request;


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
public class BasePageRequest extends BaseRequest {

    /**
     * page info see {@link PageRequest}
     */
    PageRequest page;


}
