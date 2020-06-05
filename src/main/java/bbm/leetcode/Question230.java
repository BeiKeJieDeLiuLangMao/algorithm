package bbm.leetcode;

import bbm.leetcode.common.TreeNode;
import java.util.Stack;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question230 {
    public static void main(String[] args) {
        System.out.println(new Question230().kthSmallest(TreeNode.build(new Integer[] {5, 3, 6, 2, 4, null, null, 1}), 3));
    }

    public int kthSmallest(TreeNode root, int k) {
        int min = 0;
        TreeNode minNode = root;
        Stack<Context> stack = new Stack<>();
        while (minNode.left != null) {
            stack.add(new Context(minNode));
            minNode = minNode.left;
        }
        stack.add(new Context(minNode));
        while (!stack.isEmpty()) {
            Context context = stack.pop();
            min++;
            if (min == k) {
                return context.node.val;
            }
            if (context.node.right != null) {
                TreeNode node = context.node.right;
                if (node.left != null) {
                    while (node.left != null) {
                        stack.push(new Context(node));
                        node = node.left;
                    }
                }
                stack.push(new Context(node));
            }
        }
        return -1;
    }

    private static class Context {
        private TreeNode node;

        private Context(TreeNode node) {
            this.node = node;
        }
    }
}
