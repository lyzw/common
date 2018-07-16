package com.sapling.common.tools.http;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class SimpleHttpUtilTest {

    @Test
    public void doGet() {
    }

    @Test
    public void doGet1() {
    }

    @Test
    public void doDelete() {
    }

    @Test
    public void doFormPost() {
    }

    @Test
    public void doFormPut() {
    }

    @Test
    public void doBodyPost() {
    }

    @Test
    public void doBodyReqeust() {
    }

    @Test
    public void doFormRequest() {
    }


    @Test
    public void genGetUrlWithParamters() {
        Map<String, String> paramters = new HashMap<>();
        paramters.put("param1", "aaaaa");
        paramters.put("param2", "bbbbb");
        paramters.put("param3", "ccccc");
        String url = SimpleHttpUtil.genGetUrlWithParamters("http://wwww.baidu.com", paramters);
        assertEquals("http://wwww.baidu.com?param1=aaaaa&param2=bbbbb&param3=ccccc", url);
    }

    @Test
    public void genFormParams() {
        Map<String, String> paramters = new HashMap<>();
        paramters.put("param1", "aaaaa");
        paramters.put("param2", "bbbbb");
        paramters.put("param3", "ccccc");
        List<NameValuePair> list = SimpleHttpUtil.genFormParams(paramters);
        assertTrue(list.size() == 3);
        assertTrue(list.contains(new BasicNameValuePair("param1", "aaaaa")));
        assertTrue(list.contains(new BasicNameValuePair("param2", "bbbbb")));
        assertTrue(list.contains(new BasicNameValuePair("param3", "ccccc")));
    }
}