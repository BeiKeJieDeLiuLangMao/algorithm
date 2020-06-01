package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 *
 * @author bbm
 * @date 2020/5/29
 */
public class Question19 {
    public static void main(String[] args) {
        print(new Question19().removeNthFromEnd(ListNode.build(new Integer[] {1, 2, 3, 4, 5}), 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head;
        for (int i = 0; i < n; i++) {
            front = front.next;
        }
        ListNode node = head;
        if (front == null) {
            return head.next;
        }
        while (front.next != null) {
            node = node.next;
            front = front.next;
        }
        removeNodeFromLink(node);
        return head;
    }

    private void removeNodeFromLink(ListNode node) {
        ListNode deleted = node.next;
        node.next = deleted.next;
        deleted.next = null;
    }
}
