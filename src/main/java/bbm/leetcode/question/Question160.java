package bbm.leetcode.question;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.ListNode.find;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *  
 * 示例 1：
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question160 {
    public static void main(String[] args) {
        ListNode a = ListNode.build(new Integer[] {4, 1, 8, 4, 5});
        ListNode b = ListNode.build(new Integer[] {5, 0, 1});
        ListNode bEnd = find(b, 2);
        bEnd.next = find(a, 2);
        System.out.println(new Question160().getIntersectionNode(a, b).val);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int aLength = computeLinkSizeAndCheckDest(headA, null);
        int bLength = computeLinkSizeAndCheckDest(headB, null);
        ListNode reversedA = reverse(headA);
        int baLength = computeLinkSizeAndCheckDest(headB, headA);
        if (baLength < 0) {
            reverse(reversedA);
            return null;
        }
        reverse(reversedA);
        int mergePointIndex = aLength - ((aLength + bLength - baLength - 1) / 2) - 1;
        return findNode(headA, mergePointIndex);
    }

    private ListNode reverse(ListNode a) {
        if (a == null || a.next == null) {
            return a;
        }
        ListNode next = a.next;
        ListNode node = a;
        a.next = null;
        while (true) {
            ListNode temp = next.next;
            next.next = node;
            node = next;
            if (temp == null) {
                return next;
            }
            next = temp;
        }
    }

    private int computeLinkSizeAndCheckDest(ListNode head, ListNode dest) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            if (dest != null && node.next == null && node != dest) {
                return -1;
            }
            node = node.next;
        }
        return length;
    }

    private ListNode findNode(ListNode head, int index) {
        ListNode node = head;
        int i = 0;
        while (node != null) {
            if (i == index) {
                return node;
            } else {
                i++;
            }
            node = node.next;
        }
        throw new RuntimeException("Not impl");
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pa = headA;
        ListNode pb = headB;
        int aSize = 1;
        while (pa.next != null) {
            pa = pa.next;
            aSize++;
        }
        int bSize = 1;
        while (pb.next != null) {
            pb = pb.next;
            bSize++;
        }
        if (pa != pb) {
            return null;
        }
        pa = headA;
        pb = headB;
        int dif = aSize - bSize;
        while (dif != 0) {
            if (dif > 0) {
                pa = pa.next;
                dif--;
            } else {
                pb = pb.next;
                dif++;
            }
        }
        while (pa != pb) {
            pa = pa.next;
            pb = pb.next;
        }
        return pa;
    }
}
