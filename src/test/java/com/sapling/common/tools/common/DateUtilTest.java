package com.sapling.common.tools.common;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/9
 * @since v1.0
 */
public class DateUtilTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Test
    public void yesterday() {
        assertTrue(DateUtil.yesterday().compareTo(new Date()) < 0);
    }

    @Test
    public void tomorrow() {
    }

    @Test
    public void nextDay() {
    }

    @Test
    public void dayBefore() {
    }

    @Test
    public void addDay() {
    }

    @Test
    public void setAndAddDate() {
    }

    @Test
    public void addDate() {
    }

    @Test
    public void dateEnding() {
    }

    @Test
    public void dateEnding1() {
    }

    @Test
    public void nextMonth() {
    }

    @Test
    public void monthEnding() {
    }

    @Test
    public void addMonth() {
    }

    @Test
    public void dateBegining() {
    }

    @Test
    public void dateBegining1() {
    }

    @Test
    public void monthBeginning() {
    }

    @Test
    public void yearBegining() {
    }

    @Test
    public void setDate() {
    }

    @Test
    public void toUnixTimestamp() {
    }

    @Test
    public void fromUnixTimestamp() {
        Long unixTimestamp = 1531123750l;
        String value = sdf.format(DateUtil.fromUnixTimestamp(unixTimestamp));
        assertEquals(value,"2018-07-09 16:09:10 000");

    }


    @Test
    public void fromTimestamp(){
        Long fromTimestamp = 1531107031270106444l;
        String value = sdf.format(DateUtil.fromTimestamp(fromTimestamp/1000000));
        assertEquals(value,"2018-07-09 11:30:31 270");

    }

    @Test
    public void fromInfluxdbTimestamp(){
        Long fromTimestamp = 1531107031270106444l;
        String value = sdf.format(DateUtil.fromInfluxdbTimestamp(fromTimestamp));
        assertEquals(value,"2018-07-09 11:30:31 270");
    }

}