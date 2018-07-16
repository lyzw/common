package com.sapling.common.tools.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/10/16.
 * @since v1.0
 */
public class WrappedHttpServletRequest extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public WrappedHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
}
