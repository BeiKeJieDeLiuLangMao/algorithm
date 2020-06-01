package bbm.leetcode;

import static bbm.leetcode.TreeNode.print;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question108 {
    public static void main(String[] args) {
        print(new Question108().sortedArrayToBST(new int[] {-10, -3, 0, 5, 9}));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return convert(nums, 0, nums.length - 1);
    }

    private TreeNode convert(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = (start + end) / 2;
        TreeNode midNode = new TreeNode(nums[mid]);
        if (mid > start) {
            midNode.left = convert(nums, start, mid - 1);
        }
        if (mid < end) {
            midNode.right = convert(nums, mid + 1, end);
        }
        return midNode;
    }
}
