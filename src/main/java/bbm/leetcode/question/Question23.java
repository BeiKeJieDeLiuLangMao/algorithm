package bbm.leetcode.question;

import bbm.leetcode.common.ListNode;
import java.util.Comparator;
import java.util.PriorityQueue;

import static bbm.leetcode.common.Utils.print;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 *
 * @author bbm
 * @date 2020/6/5
 */
public class Question23 {
    public static void main(String[] args) {
        print(new Question23().mergeKLists(new ListNode[] {
            ListNode.build(new Integer[] {1, 4, 5}),
            ListNode.build(new Integer[] {1, 3, 4}),
            ListNode.build(new Integer[] {2, 6}),
        }));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode head = null;
        ListNode node = null;
        while (true) {
            boolean hasElement = false;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    priorityQueue.add(lists[i]);
                    lists[i] = lists[i].next;
                    hasElement = true;
                }
            }
            if (!hasElement) {
                break;
            }
        }
        while (!priorityQueue.isEmpty()) {
            if (head == null) {
                head = priorityQueue.poll();
            }
            if (node == null) {
                node = head;
            } else {
                node.next = priorityQueue.poll();
                node = node.next;
            }
        }
        if (node != null) {
            node.next = null;
        }
        return head;
    }
}
