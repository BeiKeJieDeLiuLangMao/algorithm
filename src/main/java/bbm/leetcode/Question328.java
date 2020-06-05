package bbm.leetcode;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question328 {
    public static void main(String[] args) {
        print(new Question328().oddEvenList(ListNode.build(new Integer[] {1, 2, 3, 4, 5})));
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode one = head.next;
        if (one == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = one;
        while (fast.next != null && fast.next.next != null) {
            ListNode newSlow = fast.next;
            ListNode newFast = fast.next.next;
            slow.next = newSlow;
            fast.next = newFast;
            slow = newSlow;
            fast = newFast;
        }
        if (fast.next != null) {
            ListNode newSlow = fast.next;
            fast.next = null;
            slow.next = newSlow;
            slow = newSlow;
        }
        slow.next = one;
        return head;
    }
}
