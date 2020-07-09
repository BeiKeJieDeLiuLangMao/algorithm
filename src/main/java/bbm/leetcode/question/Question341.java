package bbm.leetcode.question;

import bbm.leetcode.common.NestedInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 *
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question341 {
    /**
     * 内存消耗小
     */
    public static class NestedIterator implements Iterator<Integer> {

        private Stack<Context> stack = new Stack<>();
        private Context current;

        public NestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(new Context(nestedList.get(i)));
            }
            if (stack.size() > 0) {
                current = stack.pop();
            }
        }

        @Override
        public Integer next() {
            Integer result;
            if (current.nestedInteger.isInteger()) {
                result = current.nestedInteger.getInteger();
                current = null;
            } else {
                NestedInteger nextItem = current.nestedInteger.getList().get(current.index);
                if (nextItem.isInteger()) {
                    result = nextItem.getInteger();
                    current.index++;
                    if (current.index >= current.nestedInteger.getList().size()) {
                        current = null;
                    }
                } else {
                    throw new RuntimeException("Should not happen");
                }
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return makeSureCurrentValid();
        }

        private boolean makeSureCurrentValid() {
            if (current == null) {
                if (stack.size() > 0) {
                    current = stack.pop();
                } else {
                    return false;
                }
            }
            if (current.nestedInteger.isInteger()) {
                return true;
            } else {
                if (current.index < current.nestedInteger.getList().size()) {
                    if (current.nestedInteger.getList().get(current.index).isInteger()) {
                        return true;
                    } else {
                        Context next = new Context(current.nestedInteger.getList().get(current.index));
                        current.index++;
                        if (current.index < current.nestedInteger.getList().size()) {
                            stack.push(current);
                        }
                        current = next;
                        return makeSureCurrentValid();
                    }
                } else {
                    if (stack.size() > 0) {
                        current = stack.pop();
                        return makeSureCurrentValid();
                    } else {
                        current = null;
                        return false;
                    }
                }
            }
        }

        private static class Context {
            private NestedInteger nestedInteger;
            private int index;

            public Context(NestedInteger nestedInteger) {
                this.nestedInteger = nestedInteger;
            }
        }
    }

    /**
     * 更快，但是内存占用大
     */
    public static class NestedIterator2 implements Iterator<Integer> {

        List<Integer> data = new ArrayList<>();
        private int index = 0;

        public NestedIterator2(List<NestedInteger> nestedList) {
            nestedList.forEach(this::addData);
        }

        private void addData(NestedInteger integer) {
            if (integer.isInteger()) {
                data.add(integer.getInteger());
            } else {
                integer.getList().forEach(this::addData);
            }
        }

        @Override
        public boolean hasNext() {
            return index < data.size();
        }

        @Override
        public Integer next() {
            return data.get(index++);
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> data = new LinkedList<>();
        List<NestedInteger> data1 = new LinkedList<>();
        data1.add(new NestedInteger(1));
        data1.add(new NestedInteger(1));
        data.add(new NestedInteger(data1));
        data.add(new NestedInteger(2));
        List<NestedInteger> data2 = new LinkedList<>();
        data2.add(new NestedInteger(1));
        data2.add(new NestedInteger(1));
        data.add(new NestedInteger(data2));
        NestedIterator2 i = new NestedIterator2(data);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
