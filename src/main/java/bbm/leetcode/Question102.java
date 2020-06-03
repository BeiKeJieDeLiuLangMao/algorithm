package bbm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static bbm.leetcode.Utils.print;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question102 {
    public static void main(String[] args) {
        print(new Question102().levelOrder(TreeNode.build(new Integer[] {3, 9, 20, null, null, 15, 7})));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            Queue<TreeNode> from;
            Queue<TreeNode> to;
            if (!queue1.isEmpty()) {
                from = queue1;
                to = queue2;
            } else {
                from = queue2;
                to = queue1;
            }
            List<Integer> levelData = new LinkedList<>();
            while (!from.isEmpty()) {
                TreeNode node = from.poll();
                levelData.add(node.val);
                if (node.left != null) {
                    to.add(node.left);
                }
                if (node.right != null) {
                    to.add(node.right);
                }
            }
            result.add(levelData);
        }
        return result;
    }
}
