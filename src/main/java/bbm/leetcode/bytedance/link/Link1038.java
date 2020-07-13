package bbm.leetcode.bytedance.link;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.Utils.print;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Link1038 {
    public static void main(String[] args) {
        print(new Link1038().reverseList(ListNode.build(new Integer[] {1, 2, 3, 4, 5})));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode previous = head;
        ListNode current = previous.next;
        previous.next = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}
