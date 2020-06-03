package bbm.leetcode;

import java.util.Stack;

/**
 * 给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。
 *
 * 结点 p 的后继是值比 p.val 大的结点中键值最小的结点。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 * 输出: 2
 * 解析: 这里 1 的顺序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * 输出: null
 * 解析: 因为给出的结点没有顺序后继，所以答案就返回 null 了。
 *
 * 注意:
 *
 * 假如给出的结点在该树中没有顺序后继的话，请返回 null
 * 我们保证树中每个结点的值是唯一的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/inorder-successor-in-bst
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question285 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new Integer[] {2, 1, 3});
        TreeNode next = new Question285().inorderSuccessor(root, root.left);
        System.out.println(next == null ? "null" : next.val);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.val < p.val && node.right != null) {
                    stack.push(node.right);
                } else if (node.val > p.val && node.left != null) {
                    stack.push(node);
                    stack.push(node.left);
                } else {
                    if (stack.size() == 0) {
                        return null;
                    } else {
                        return stack.peek();
                    }
                }
            }
            return null;
        }
    }
}
