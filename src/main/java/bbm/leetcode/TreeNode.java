package bbm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author bbm
 * @date 2020/5/20
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode build(Integer[] values) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                nodeMap.computeIfAbsent(i, integer -> new TreeNode(values[integer]));
                if (i > 0) {
                    double parent = ((double) i + 1) / 2 - 1;
                    if (((int) parent) == parent) {
                        nodeMap.get((int) parent).left = nodeMap.get(i);
                    } else {
                        nodeMap.get((int) parent).right = nodeMap.get(i);
                    }
                }
            }
        }
        return nodeMap.get(0);
    }

    public static void print(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            if (node == null) {
                System.out.print("null ");
                continue;
            }
            System.out.print(node.val + " ");
            queue.add(node.left);
            queue.add(node.right);
        }
        System.out.println();
    }
}
