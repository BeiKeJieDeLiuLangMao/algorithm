package bbm.leetcode.offer;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 *
 * @author bbm
 * @date 2020/7/3
 */
public class Offer9 {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(3);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
        System.out.println();
        obj = new CQueue();
        System.out.println(obj.deleteHead());
        obj.appendTail(5);
        obj.appendTail(2);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
    }

    static class CQueue {

        Stack<Integer> stackLeft = new Stack<>();
        Stack<Integer> stackRight = new Stack<>();

        public CQueue() {

        }

        /**
         * 实现思想是用两个栈来进行数据的转移，新加入的节点时，先将右栈的节点放入左栈，然后把新节点放入右栈的栈低，然后在将左栈的数据推回右栈
         * 这样，右栈的数据顺序就是FIFO的顺序了
         */
        public void appendTail(int value) {
            while (!stackRight.isEmpty()) {
                stackLeft.push(stackRight.pop());
            }
            stackRight.push(value);
            while (!stackLeft.isEmpty()) {
                stackRight.push(stackLeft.pop());
            }
        }

        public int deleteHead() {
            if (stackRight.isEmpty()) {
                return -1;
            }
            return stackRight.pop();
        }
    }
}
