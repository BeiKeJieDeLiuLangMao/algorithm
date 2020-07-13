package bbm.leetcode.bytedance.link;

import bbm.leetcode.common.ListNode;

import static bbm.leetcode.common.Utils.print;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Link1022 {
    public static void main(String[] args) {
        print(new Link1022().addTwoNumbers(ListNode.build(new Integer[] {2, 4, 3}), ListNode.build(new Integer[] {5, 6, 4})));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = null;
        ListNode resultNode = null;
        ListNode leftNode = l1;
        ListNode rightNode = l2;
        while (leftNode != null && rightNode != null) {
            int sum = leftNode.val + rightNode.val;
            leftNode = leftNode.next;
            rightNode = rightNode.next;
            if (resultHead == null) {
                resultHead = new ListNode(sum);
                resultNode = resultHead;
            } else {
                resultNode.next = new ListNode(sum);
                resultNode = resultNode.next;
            }
        }
        if (leftNode != null) {
            if (resultHead == null) {
                resultHead = leftNode;
            } else {
                resultNode.next = leftNode;
            }
        }
        if (rightNode != null) {
            if (resultHead == null) {
                resultHead = rightNode;
            } else {
                resultNode.next = rightNode;
            }
        }
        resultNode = resultHead;
        while (resultNode != null) {
            if (resultNode.val > 9) {
                int nextAddValue = resultNode.val / 10;
                resultNode.val = resultNode.val % 10;
                if (resultNode.next != null) {
                    resultNode.next.val += nextAddValue;
                } else {
                    resultNode.next = new ListNode(nextAddValue);
                }
            }
            resultNode = resultNode.next;
        }
        return resultHead;
    }
}
