package com.sapling.common.tools.common;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/7
 * @since v1.0
 */
public class ObjectUtilTest {

    @Test
    public void deepCopy() {


    }

    @Test
    public void bytesToObject() {


    }

    @Test
    public void objectToBytes() throws IOException {

        System.out.println(ByteUtil.bytesToHex(ObjectUtil.ObjectToBytes(null)));
    }
}