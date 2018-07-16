package com.sapling.common.tools.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/14
 * @since v1.0
 */
public class ByteUtilTest {

    @Test
    public void shortToBytes() {
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<0)).shortValue()),new byte[]{(byte)0,(byte)(1<<0)});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<1)).shortValue()),new byte[]{(byte)0,(byte)(1<<1)});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<2)).shortValue()),new byte[]{(byte)0,(byte)(1<<2)});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<3)).shortValue()),new byte[]{(byte)0,(byte)(1<<3)});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<4)).shortValue()),new byte[]{(byte)0,(byte)(1<<4)});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<8)).shortValue()),new byte[]{(byte)1,(byte)0});
        Assert.assertArrayEquals(ByteUtil.shortToBytes(((Integer)(1<<12)).shortValue()),new byte[]{(byte)(1<<4),(byte)0});
    }

    @Test
    public void bytesToShort() {
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(1<<0)}),((Integer)(1<<0)).shortValue());
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(1<<1)}),((Integer)(1<<1)).shortValue());
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(1<<2)}),((Integer)(1<<2)).shortValue());
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(1<<4)}),((Integer)(1<<4)).shortValue());
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(1<<6)}),((Integer)(1<<6)).shortValue());
        Assert.assertSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(127)}),((Integer)(127)).shortValue());
        Assert.assertNotSame(ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(128)}),((Integer)(128)).shortValue());
        Assert.assertEquals((Short)ByteUtil.bytesToShort(new byte[]{(byte)0,(byte)(128)}),(Short)((Integer)(128)).shortValue());
        //大于127的数据不能用assertSame，jvm在
        Assert.assertEquals((Short)ByteUtil.bytesToShort(new byte[]{(byte)(1 << 0),(byte)0}),(Short)((Integer)(1<<8)).shortValue());
        Assert.assertEquals((Short)ByteUtil.bytesToShort(new byte[]{(byte)(1 << 4),(byte)0}),(Short)((Integer)(1<<12)).shortValue());
    }

    @Test
    public void intToBytes() {
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<0),new byte[]{(byte)0,(byte)0,(byte)0,(byte)(1<<0)});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<1),new byte[]{(byte)0,(byte)0,(byte)0,(byte)(1<<1)});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<2),new byte[]{(byte)0,(byte)0,(byte)0,(byte)(1<<2)});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<3),new byte[]{(byte)0,(byte)0,(byte)0,(byte)(1<<3)});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<4),new byte[]{(byte)0,(byte)0,(byte)0,(byte)(1<<4)});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<8),new byte[]{(byte)0,(byte)0,(byte)(1<<0),(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<12),new byte[]{(byte)0,(byte)0,(byte)(1<<4),(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<16),new byte[]{(byte)0,(byte)(1<<0),(byte)(0),(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<20),new byte[]{(byte)0,(byte)(1<<4),(byte)0,(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<24),new byte[]{(byte)(1<<0),(byte)0,(byte)0,(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<28),new byte[]{(byte)(1<<4),(byte)0,(byte)0,(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(1<<31),new byte[]{(byte)(1<<7),(byte)0,(byte)0,(byte)0});
        Assert.assertArrayEquals(ByteUtil.intToBytes(Integer.MAX_VALUE),new byte[]{(byte)(127),(byte)255,(byte)255,(byte)255});
        Assert.assertArrayEquals(ByteUtil.intToBytes(Integer.MIN_VALUE),new byte[]{(byte)(128),(byte)0,(byte)0,(byte)0});
    }

    @Test
    public void bytesToInt() {
    }

    @Test
    public void longToBytes() {
    }

    @Test
    public void bytesToLong() {
    }

    @Test
    public void bytesToHex() {
    }

    @Test
    public void hexToBytes() {
    }

    @Test
    public void main() {
    }
}