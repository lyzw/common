package com.sapling.common.tools.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/17
 * @since v1.0
 */
public class NormalIOUtilTest {

    @Test
    public void toBytes() {
    }

    @Test
    public void toString2() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("你好，sssss".getBytes("utf-8"));
        System.out.println(InputStreamUtil.toString(byteArrayInputStream));
    }

    @Test
    public void toString1() {
    }

    @Test
    public void input2Output() {
    }
}