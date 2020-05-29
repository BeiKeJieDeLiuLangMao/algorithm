package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode part = split(head);
        if (part != null) {
            ListNode left = sortList(head);
            ListNode right = sortList(part);
            ListNode merge = null;
            ListNode node = null;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    if (merge == null) {
                        merge = new ListNode(left.val);
                        node = merge;
                    } else {
                        node.next = new ListNode(left.val);
                        node = node.next;
                    }
                    left = left.next;
                } else {
                    if (merge == null) {
                        merge = new ListNode(right.val);
                        node = merge;
                    } else {
                        node.next = new ListNode(right.val);
                        node = node.next;
                    }
                    right = right.next;
                }
            }
            if (left != null) {
                node.next = left;
            } else {
                node.next = right;
            }
            return merge;
        } else {
            return head;
        }
    }

    private ListNode split(ListNode node) {
        if (node.next == null) {
            return null;
        }
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow != fast) {
            node = slow.next;
            slow.next = null;
            return node;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        print(new Question148().sortList(ListNode.build(new Integer[] {4, 2, 1, 3})));
    }
}
