package com.sapling.common.tools.common;

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
        assertEquals("AaAaAa", StringUtil.upperFirstLetter("aaAaAa"));
        assertEquals("1aAaAa", StringUtil.upperFirstLetter("1aAaAa"));
        assertEquals("_aAaAa", StringUtil.upperFirstLetter("_aAaAa"));
        assertEquals("", StringUtil.upperFirstLetter(""));
        assertEquals(null, StringUtil.upperFirstLetter(null));

    }

    @Test
    public void underLineToCamel() {
        assertEquals("aaAaAa", StringUtil.toCamel("aa_aa_aa"));
        assertEquals("aa1aAa", StringUtil.toCamel("aa_1a_aa"));
        assertEquals("", StringUtil.toCamel(""));
        assertEquals(null, StringUtil.toCamel(null));
        assertEquals("aaaaa", StringUtil.toCamel("aaaaa"));

    }

    @Test
    public void toUnderLine() {

        assertEquals("aa_aa_aa", StringUtil.toUnderLine("aaAaAa"));
        assertEquals("aa_a_ass", StringUtil.toUnderLine("aaAAss"));
        assertEquals("", StringUtil.toUnderLine(""));
        assertEquals(null, StringUtil.toUnderLine(null));
    }
}