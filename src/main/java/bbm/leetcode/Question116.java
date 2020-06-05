package bbm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 示例：
 *
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 *
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 *
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * 提示：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 *
 * @author bbm
 * @date 2020/6/5
 */
public class Question116 {
    public static void main(String[] args) {
        Node.print(new Question116().connect(Node.build(new Integer[] {1, 2, 3, 4, 5, 6, 7})));
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        handleNextPoint(root, null);
        Node rightNode = root;
        while (rightNode != null) {
            rightNode.next = null;
            rightNode = rightNode.right;
        }
        return root;
    }

    private void handleNextPoint(Node node, Node parent) {
        if (parent != null && parent.next != null) {
            node.next = parent.next.next;
        }
        if (node.left != null) {
            handleNextPoint(node.left, node);
            handleNextPoint(node.right, node);
        }
        if (parent != null) {
            if (parent.next != null) {
                parent.next.next = node;
            }
            parent.next = node;
        }
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        private Node(int val) {
            this.val = val;
        }

        private static Node build(Integer[] values) {
            Map<Integer, Node> nodeMap = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    nodeMap.computeIfAbsent(i, integer -> new Node(values[integer]));
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

        public static void print(Node root) {
            if (root == null) {
                return;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (queue.size() > 0) {
                Node node = queue.poll();
                if (node == null) {
                    System.out.print("null ");
                    continue;
                }
                System.out.print(node.val + ":" + (node.next == null ? "null" : node.next.val) + " ");
                queue.add(node.left);
                queue.add(node.right);
            }
            System.out.println();
        }
    }
}
