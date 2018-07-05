package com.sapling.common.api.base;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class BasePageResponse extends BaseResponse {

    private PageInfo page;

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
