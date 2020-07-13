package bbm.leetcode.bytedance.link;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.Utils.print;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Link1048 {

    public static void main(String[] args) {
        print(new Link1048().mergeTwoLists(ListNode.build(new Integer[] {1, 2, 4}), ListNode.build(new Integer[] {1, 3, 4})));
    }

    /**
     * 顺序比对两个链表，发现一个小的就接入结果链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode headNode = null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null && node2 != null) {
            ListNode current;
            if (node1.val <= node2.val) {
                current = node1;
                node1 = node1.next;
            } else {
                current = node2;
                node2 = node2.next;
            }
            if (head == null) {
                head = current;
                headNode = current;
            } else {
                headNode.next = current;
                headNode = headNode.next;
            }
        }
        if (node1 != null) {
            if (head == null) {
                head = node1;
                headNode = node1;
            } else {
                headNode.next = node1;
            }
        }
        if (node2 != null) {
            if (head == null) {
                head = node2;
            } else {
                headNode.next = node2;
            }
        }
        return head;
    }
}
