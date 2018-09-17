package com.sapling.common.tools.regex;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/6
 * @since v1.0
 */
public abstract class SimpleStringChecker implements Checker {

    protected String regex;

    @Override
    public boolean check(Object value) {
        String checker = (String) value;
        return RegexUtil.isMatch(regex, checker) && internalCheck(checker);
    }

    /**
     * 内部校验
     * @param value
     * @return
     */
    abstract boolean internalCheck(String value);

}
