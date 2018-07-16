package com.sapling.common.tools.file;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 *
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/12.
 * @since v1.0
 */
public class ExcludeTypeFileFilter implements FileFilter {

    private List<String> excludeType;

    @Override
    public boolean accept(File pathname) {
        for (String type:excludeType){
            if (pathname.getName().endsWith(type)){
                return false;
            }
        }
        return true;
    }

    public List<String> getExcludeType() {
        return excludeType;
    }

    public void setExcludeType(List<String> excludeType) {
        this.excludeType = excludeType;
    }
}
