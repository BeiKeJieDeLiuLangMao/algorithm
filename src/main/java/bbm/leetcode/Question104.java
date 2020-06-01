package bbm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import static bbm.leetcode.TreeNode.build;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question104 {
    public static void main(String[] args) {
        System.out.println(new Question104().maxDepth2(build(new Integer[]{3, 9, 20, null, null, 15, 7})));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    /**
     * 迭代
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        Queue<TreeNode> current = queue1;
        Queue<TreeNode> next = queue2;
        int result = 0;
        while (current.size() > 0) {
            TreeNode node = current.poll();
            if (node.left != null) {
                next.add(node.left);
            }
            if (node.right != null) {
                next.add(node.right);
            }
            if (current.size() == 0) {
                result++;
                Queue<TreeNode> temp = next;
                next = current;
                current = temp;
            }
        }
        return result;
    }
}
