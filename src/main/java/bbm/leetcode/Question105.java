package bbm.leetcode;

import bbm.leetcode.common.TreeNode;

import static bbm.leetcode.common.TreeNode.print;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question105 {
    public static void main(String[] args) {
        print(new Question105().buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7}));
    }

    public TreeNode buildTree(int[] preOrder, int[] inorder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }
        return handleChild(preOrder, inorder, 0, 0, inorder.length - 1, null, true).node;
    }

    private Context handleChild(int[] preOrder, int[] inorder, int preOrderIndex, int inorderStart, int inorderEnd,
        TreeNode parent, boolean left) {
        if (preOrderIndex < preOrder.length) {
            TreeNode newNode = new TreeNode(preOrder[preOrderIndex++]);
            if (parent != null) {
                if (left) {
                    parent.left = newNode;
                } else {
                    parent.right = newNode;
                }
            }
            int midIndex = getIndex(inorder, inorderStart, inorderEnd, newNode.val);
            int leftEnd = midIndex - 1;
            int rightStart = midIndex + 1;
            if (inorderStart <= leftEnd) {
                preOrderIndex = handleChild(preOrder, inorder, preOrderIndex, inorderStart, leftEnd, newNode, true).preOrderIndex;
            }
            if (rightStart <= inorderEnd) {
                preOrderIndex = handleChild(preOrder, inorder, preOrderIndex, rightStart, inorderEnd, newNode, false).preOrderIndex;
            }
            return new Context(newNode, preOrderIndex);
        } else {
            return new Context(null, preOrderIndex);
        }
    }

    /**
     * 可以建立一个map 快速完成从 num 到 index 的匹配
     */
    private int getIndex(int[] nums, int start, int end, int num) {
        for (int i = start; i <= end; i++) {
            if (nums[i] == num) {
                return i;
            }
        }
        return -1;
    }

    private static class Context {
        private TreeNode node;
        private int preOrderIndex;

        private Context(TreeNode node, int preOrderIndex) {
            this.node = node;
            this.preOrderIndex = preOrderIndex;
        }
    }
}
