package bbm.leetcode;

import java.util.List;

/**
 * @author bbm
 * @date 2020/6/1
 */
public class NestedInteger {

    private boolean integer;
    private int value;
    private List<NestedInteger> list;

    public boolean isInteger() {
        return integer;
    }
    public Integer getInteger() {
        return value;
    }
    public List<NestedInteger> getList() {
        return list;
    }

    public NestedInteger(int value) {
        this.integer = true;
        this.value = value;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.integer = false;
        this.list = list;
    }
}
