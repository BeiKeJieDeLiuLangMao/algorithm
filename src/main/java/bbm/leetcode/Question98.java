package bbm.leetcode;

import bbm.leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 *
 * @author bbm
 * @date 2020/6/4
 */
public class Question98 {
    public static void main(String[] args) {
        System.out.println(new Question98().isValidBST(TreeNode.build(new Integer[] {2, 1, 3})));
    }

    public boolean isValidBST(TreeNode root) {
        Queue<Context> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new Context(root));
        }
        while (!queue.isEmpty()) {
            Context context = queue.poll();
            if ((context.hasMax && context.node.val >= context.max) ||
                (context.hasMin && context.node.val <= context.min)) {
                return false;
            }
            if (context.node.left != null) {
                if (context.node.left.val >= context.node.val) {
                    return false;
                }
                Context child = new Context(context.node.left);
                child.setMin(context);
                child.setMax(context.node.val);
                queue.add(child);
            }
            if (context.node.right != null) {
                if (context.node.right.val <= context.node.val) {
                    return false;
                }
                Context child = new Context(context.node.right);
                child.setMax(context);
                child.setMin(context.node.val);
                queue.add(child);
            }
        }
        return true;
    }

    private static class Context {
        private int min;
        private boolean hasMin;
        private int max;
        private boolean hasMax;
        private TreeNode node;

        private Context(TreeNode node) {
            this.node = node;
        }

        private void setMin(Context base) {
            if (base.hasMin) {
                this.hasMin = true;
                this.min = base.min;
            }
        }

        private void setMax(Context base) {
            if (base.hasMax) {
                this.hasMax = true;
                this.max = base.max;
            }
        }

        private void setMin(int min) {
            this.min = min;
            this.hasMin = true;
        }

        private void setMax(int max) {
            this.max = max;
            this.hasMax = true;
        }
    }
}
