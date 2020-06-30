package com.sapling.common.base.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
@Data
@EqualsAndHashCode
public class BasePageResponse<T> extends BaseResponse<List<T>> {

    private PageInfo page;


}
