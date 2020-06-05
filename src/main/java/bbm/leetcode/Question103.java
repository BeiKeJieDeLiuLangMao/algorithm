package bbm.leetcode;

import bbm.leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question103 {
    public static void main(String[] args) {
        print(new Question103().zigzagLevelOrder(TreeNode.build(new Integer[] {3, 9, 20, null, null, 15, 7})));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<List<Integer>> result = new LinkedList<>();
        boolean useFirstStack = true;
        if (root != null) {
            stack1.add(root);
        }
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (useFirstStack) {
                List<Integer> partResult = new LinkedList<>();
                while (!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    partResult.add(node.val);
                    if (node.left != null) {
                        stack2.add(node.left);
                    }
                    if (node.right != null) {
                        stack2.add(node.right);
                    }
                }
                result.add(partResult);
                useFirstStack = false;
            } else {
                List<Integer> partResult = new LinkedList<>();
                while (!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    partResult.add(node.val);
                    if (node.right != null) {
                        stack1.add(node.right);
                    }
                    if (node.left != null) {
                        stack1.add(node.left);
                    }
                }
                result.add(partResult);
                useFirstStack = true;
            }
        }
        return result;
    }
}
