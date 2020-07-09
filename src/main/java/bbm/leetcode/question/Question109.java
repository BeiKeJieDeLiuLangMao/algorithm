package bbm.leetcode.question;

import bbm.leetcode.common.ListNode;
import bbm.leetcode.common.TreeNode;

import static bbm.leetcode.common.TreeNode.print;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 *
 * @author bbm
 * @date 2020/7/3
 */
public class Question109 {
    public static void main(String[] args) {
        print(new Question109().sortedListToBST(ListNode.build(new Integer[] {-10, -3, 0, 5, 9})));
    }

    public TreeNode sortedListToBST(ListNode head) {
        return convert(head, null);
    }

    private TreeNode convert(ListNode begin, ListNode end) {
        ListNode mid = findMid(begin, end);
        if (mid == null) {
            return null;
        }
        TreeNode node = new TreeNode(mid.val);
        if (begin != mid) {
            node.left = convert(begin, mid);
        }
        if (end != mid) {
            node.right = convert(mid.next, end);
        }
        return node;
    }

    /**
     * 利用快慢指针找 mid
     */
    private ListNode findMid(ListNode begin, ListNode end) {
        if (begin == end) {
            return null;
        }
        if (begin == null) {
            return null;
        }
        ListNode slow = begin;
        ListNode fast = begin;
        while (fast != end && fast != null) {
            if (fast.next == end) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
