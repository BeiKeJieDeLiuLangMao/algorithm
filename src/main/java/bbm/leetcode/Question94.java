package bbm.leetcode;

import bbm.leetcode.common.TreeNode;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question94 {

    public static void main(String[] args) {
        print(new Question94().inorderTraversal2(TreeNode.build(new Integer[] {1, null, 2, null, null, 3, 4})));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        visit(root, result);
        return result;
    }

    private void visit(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        visit(node.left, result);
        result.add(node.val);
        visit(node.right, result);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }
        Set<TreeNode> leftHandledSet = new HashSet<>();
        Set<TreeNode> rightHandledSet = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            boolean couldRemove = false;
            if (node.left == null || leftHandledSet.contains(node)) {
                result.add(node.val);
                couldRemove = true;
            }
            if (!rightHandledSet.contains(node)) {
                if (node.right != null) {
                    stack.push(node.right);
                }
                rightHandledSet.add(node);
            }
            if (!couldRemove) {
                stack.push(node);
                if (!leftHandledSet.contains(node)) {
                    stack.push(node.left);
                    leftHandledSet.add(node);
                }
            }
        }
        return result;
    }
}
