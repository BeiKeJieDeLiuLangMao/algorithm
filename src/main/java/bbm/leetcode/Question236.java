package bbm.leetcode;

import bbm.leetcode.common.TreeNode;
import java.util.Stack;

import static bbm.leetcode.common.TreeNode.build;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question236 {

    public static void main(String[] args) {
        System.out.println(new Question236().lowestCommonAncestor(build(new Integer[] {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}),
            new TreeNode(5), new TreeNode(4)).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Context> stackP = dfs(root, p);
        Stack<Context> stackQ = dfs(root, q);
        while (stackP.size() != stackQ.size()) {
            if (stackP.size() > stackQ.size()) {
                stackP.pop();
            } else {
                stackQ.pop();
            }
        }
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode pParent = stackP.pop().node;
            TreeNode qParent = stackQ.pop().node;
            if (pParent.val == qParent.val) {
                return pParent;
            }
        }
        return null;
    }

    private Stack<Context> dfs(TreeNode root, TreeNode target) {
        Stack<Context> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.add(new Context(node));
            node = node.left;
        }
        while (!stack.isEmpty()) {
            if (stack.peek().node.val == target.val) {
                break;
            } else if (stack.peek().node.right != null && !stack.peek().rightHandled) {
                stack.peek().rightHandled = true;
                node = stack.peek().node.right;
                while (node != null) {
                    stack.add(new Context(node));
                    node = node.left;
                }
            } else {
                stack.pop();
            }
        }
        return stack;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Context> stackP = dfs(root, p);
        Stack<Context> stackQ = dfs(root, q);
        while (stackP.size() != stackQ.size()) {
            if (stackP.size() > stackQ.size()) {
                stackP.pop();
            } else {
                stackQ.pop();
            }
        }
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode pParent = stackP.pop().node;
            TreeNode qParent = stackQ.pop().node;
            if (pParent.val == qParent.val) {
                return pParent;
            }
        }
        return null;
    }

    private static class Context {
        TreeNode node;
        boolean rightHandled;

        public Context(TreeNode node) {
            this.node = node;
        }
    }
}
