package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 * <p>
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question237 {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new Integer[] {4, 5, 1, 9});
        ListNode listNode = head.next;
        new Question237().deleteNode(listNode);
        print(head);
    }

    public void deleteNode(ListNode node) {
        while (node != null && node.next != null) {
            ListNode next = node.next;
            node.val = next.val;
            if (next.next == null) {
                node.next = null;
                return;
            }
            node = next;
        }
    }
}
