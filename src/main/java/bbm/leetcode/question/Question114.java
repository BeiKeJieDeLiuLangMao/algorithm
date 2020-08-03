package bbm.leetcode.question;

import bbm.leetcode.common.TreeNode;

import static bbm.leetcode.common.TreeNode.print;

/**
 * @author bbm
 * @date 2020/8/3
 */
public class Question114 {
    public static void main(String[] args) {
        TreeNode data = TreeNode.build(new Integer[] {1, 2, 5, 3, 4, null, 6});
        new Question114().flatten(data);
        print(data);
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(null, root);
    }

    /**
     * 思路是通过递归来解决，每次给定当前要挂载的 parent 节点和要挂载的目标节点，返回挂载结束之后最下层节点的指针，
     * 当 parent 为null 时表示刚开始运作，我们让 node 作为 newParent，然后分别把左节点 和 右节点挂载上去，
     * 当 parent 不为 null 时，我们先清空左指针，因为栈中保存了 parent 的左节点，然后将 parent 的 右指针指向 node
     * 紧接着，我们让 node 作为 newParent 再递归的处理 node 的左节点 和 右节点
     */
    public TreeNode flatten(TreeNode parent, TreeNode node) {
        if (parent == null) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode newParent = flatten(node, left);
            return flatten(newParent, right);
        } else {
            if (node == null) {
                return parent;
            } else {
                parent.left = null;
                parent.right = node;
                TreeNode left = node.left;
                TreeNode right = node.right;
                TreeNode newParent = flatten(node, left);
                return flatten(newParent, right);
            }
        }
    }
}
