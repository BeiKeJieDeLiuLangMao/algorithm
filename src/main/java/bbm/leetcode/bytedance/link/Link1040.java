package bbm.leetcode.bytedance.link;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.Utils.print;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
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
 * @author bbm
 * @date 2020/7/13
 */
public class Link1040 {
    public static void main(String[] args) {
        print(new Link1040().sortList(ListNode.build(new Integer[] {-1, 5, 3, 4, 0})));
    }

    /**
     * 使用归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = getEndNode(null, head);
        return mergeSort(null, head, node, null);
    }

    private ListNode mergeSort(ListNode previous, ListNode begin, ListNode end, ListNode later) {
        if (begin == end) {
            return begin;
        }
        ListNode mid = findMid(begin, end);
        ListNode rightBegin = mid.next;
        ListNode leftHead = mergeSort(previous, begin, mid, rightBegin);
        ListNode leftEnd = getEndNode(rightBegin, leftHead);
        ListNode rightHead = mergeSort(leftEnd, rightBegin, end, later);
        return merge(previous, later, leftHead, rightHead);
    }

    private ListNode getEndNode(ListNode endNext, ListNode begin) {
        ListNode leftEnd = begin;
        while (leftEnd.next != endNext) {
            leftEnd = leftEnd.next;
        }
        return leftEnd;
    }

    private ListNode merge(ListNode previous, ListNode later, ListNode leftNode, ListNode rightHead) {
        ListNode rightNode = rightHead;
        ListNode head = null;
        ListNode node = null;
        while (leftNode != rightHead && rightNode != later) {
            if (leftNode.val <= rightNode.val) {
                if (head == null) {
                    head = leftNode;
                    node = leftNode;
                } else {
                    node.next = leftNode;
                    node = node.next;
                }
                leftNode = leftNode.next;
            } else {
                if (head == null) {
                    head = rightNode;
                    node = rightNode;
                } else {
                    node.next = rightNode;
                    node = node.next;
                }
                rightNode = rightNode.next;
            }
        }
        if (leftNode != rightHead) {
            if (head == null) {
                head = leftNode;
                node = leftNode;
            } else {
                node.next = leftNode;
                while (node.next != rightHead) {
                    node = node.next;
                }
                node.next = later;
            }
        }
        if (rightNode != later) {
            if (head == null) {
                head = rightNode;
            } else {
                node.next = rightNode;
            }
        }
        if (previous != null) {
            previous.next = head;
        }
        return head;
    }

    private ListNode findMid(ListNode begin, ListNode end) {
        ListNode slow = begin;
        ListNode fast = begin.next;
        while (fast != end) {
            slow = slow.next;
            if (fast.next != end) {
                fast = fast.next.next;
            } else {
                break;
            }
        }
        return slow;
    }
}
