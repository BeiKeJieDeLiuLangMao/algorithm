package bbm.leetcode;

import static bbm.leetcode.TreeNode.build;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *  
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question101 {

    public static void main(String[] args) {
        System.out.println(new Question101().isSymmetric(build(new Integer[]{1, 2, 3})));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        return check2NodeSymmetric(root.left, root.right);
    }

    public boolean check2NodeSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null) {
            return false;
        }
        if (right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        boolean result = true;
        if (left.left != null && right.right != null) {
            if (left.left.val != right.right.val) {
                result = false;
            }
        } else {
            result = left.left == null && right.right == null;
        }
        if (result) {
            if (left.right != null && right.left != null) {
                if (left.right.val != right.left.val) {
                    return false;
                }
            } else {
                result = left.right == null && right.left == null;
            }
        }
        if (result) {
            return check2NodeSymmetric(left.left, right.right) && check2NodeSymmetric(left.right, right.left);
        }
        return false;
    }
}
