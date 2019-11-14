package com.sapling.tools.collection;

import com.sapling.common.base.api.request.BaseRequest;
import com.sapling.common.tools.collection.MapUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @author weizhou
 * @version v1.0
 * @date 2018/6/20
 * @since v1.0
 */
public class MapUtilTest {

    @Test
    public void objectToMap() {
        BaseRequest request = new BaseRequest();
        request.setRequestId("test");
        request.setRequestTime(12345678l);
        Map one = new HashMap();
        Map map = MapUtil.objectToMap(request);

        System.out.println(map);
        System.out.println(one);
//        Assert.assertFalse(MapUtil.compare(map,one));
    }

    @Test
    public void mapToObject() {
    }
}