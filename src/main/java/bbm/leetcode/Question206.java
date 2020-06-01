package bbm.leetcode;

import static bbm.leetcode.ListNode.build;
import static bbm.leetcode.Utils.print;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question206 {

    public static void main(String[] args) {
        print(new Question206().reverseList(build(new Integer[]{1, 2, 3, 4, 5})));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        ListNode next = head.next;
        head.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = node;
            node = next;
            next = temp;
        }
        return node;
    }
}
