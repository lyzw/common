package com.sapling.common.tools.common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class ChineseCharUtilTest {

    @Test
    public void isChineseChar() {
        assertTrue(ChineseCharUtil.isChineseChar('是'));
        assertFalse(ChineseCharUtil.isChineseChar('a'));
    }

    @Test
    public void includeChinese() {
        assertFalse(ChineseCharUtil.includeChinese("abc"));
        assertTrue(ChineseCharUtil.includeChinese("abc是"));
        assertFalse(ChineseCharUtil.includeChinese(null));
        assertFalse(ChineseCharUtil.includeChinese(""));

    }
}