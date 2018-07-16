package com.sapling.common.tools.security;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/20
 * @since v1.0
 */
public class MD5UtilTest {

    @Test
    public void md5() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String value1 = "fda;fnqelr;fmqwfqwfqwfqefebyfuqiwy0qpwfmf,mg67f290-ri2 jfefn w";
        String value2 = "sfaf发多少粉丝纷纷纷纷来发；问佛位分别为更名为给你问过我恶魔反而分为热吻；疯狂为何物个发票开满了看不够颓废体育健康教育科技";
        assertEquals("d2e13605c66073054ff1e92bfd84b3fd",MD5Util.md5(value1));
        assertEquals("c49ae9f4f2008c42c3929b44daa94873",MD5Util.md5(value2));
    }

    @Test
    public void md51() {
    }

    @Test
    public void md52() {
    }
}