package bbm.leetcode;

import java.util.ArrayList;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question234 {
    public static void main(String[] args) {
        System.out.println(new Question234().isPalindrome2(ListNode.build(new Integer[]{1, 0, 0})));
    }

    public boolean isPalindrome(ListNode head) {
        int size;
        ListNode node;
        ArrayList<Integer> queue = new ArrayList<>();
        if (head == null) {
            return true;
        } else {
            size = 1;
            node = head.next;
            queue.add(head.val);
        }
        while (node != null) {
            size++;
            queue.add(node.val);
            node = node.next;
        }
        int left;
        int right;
        if (size % 2 == 0) {
            left = Math.max(0, size / 2 - 1);
            right = Math.min(size / 2, size - 1);
        } else {
            left = Math.max(0, size / 2 - 1);
            right = Math.min(size / 2 + 1, size - 1);
        }
        for (int i = left, j = right; i >= 0 && j < queue.size(); i--, j++) {
            if (!queue.get(i).equals(queue.get(j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 快慢指针，快速找到尾结点，然后反转指针达到空间 O(1)
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null) {
            slow = slow.next;
            if (fast.next.next == null) {
                fast = fast.next;
            } else {
                fast = fast.next.next;
            }
        }
        ListNode node = slow;
        ListNode next = node.next;
        while (next != null) {
            ListNode temp = next.next;
            next.next = node;
            node = next;
            next = temp;
        }
        ListNode left = head;
        ListNode right = fast;
        while (left != right) {
            if (left.val != right.val) {
                return false;
            } else {
                if (left.next == right && right.next == left) {
                    return true;
                }
                left = left.next;
                right = right.next;
            }
        }
        return true;
    }
}
