package com.sapling.tools.common;

import com.sapling.common.tools.common.StringUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class StringUtilTest {

    @Test
    public void isEmpty() {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(null));
        assertFalse(StringUtil.isEmpty("a"));
    }

    @Test
    public void upperFirstLetter() {
        assertEquals("AaAaAa",StringUtil.upperFirstLetter("aaAaAa"));
        assertEquals("1aAaAa",StringUtil.upperFirstLetter("1aAaAa"));
        assertEquals("_aAaAa",StringUtil.upperFirstLetter("_aAaAa"));
        assertEquals("",StringUtil.upperFirstLetter(""));
        assertEquals(null,StringUtil.upperFirstLetter(null));

    }

    @Test
    public void underLineToCamel() {
        assertEquals("aaAaAa",StringUtil.toCamel("aa_aa_aa"));
        assertEquals("aa1aAa",StringUtil.toCamel("aa_1a_aa"));

    }
}