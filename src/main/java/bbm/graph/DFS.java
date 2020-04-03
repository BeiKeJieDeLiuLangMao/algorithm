package bbm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bbm.graph.DFS.Color.BLACK;
import static bbm.graph.DFS.Color.GRAY;
import static bbm.graph.DFS.Color.WHITE;

/**
 * 图的广度优先搜索，用颜色来表示是否已经开始处理某一个节点，白色：未处理，灰色：处理中，黑色：处理完
 *
 * 如果我们将每个节点的开始处理时间记为 startTime 处理完成时间记录为 endTime 那么我们可以通过 startTime 和 endTime 够成一个括号化结构
 * Node x, start:4 end:5 被包含在 Node y, start:3 end:6 之中，这实际上就是一个树形结构, 当 x 节点的 start 和 end 在 y 节点的
 * start end 范围内时，x 节点就是 y 节点的子节点
 *     y(3, 6)
 *     /
 *  x（4，5）
 *
 * 通过这个深度优先树，我们可以简单的通过检查是否有由子节点到父节点的边来判断图中是否有环
 * @author bbm
 */
public class DFS {
    public static class Node {
        String name;
        Color color = WHITE;
        int startTime;
        int endTime;
        Node pre = null;
        List<Node> neighbors = new LinkedList<>();

        public Node(String name) {
            this.name = name;
        }
    }

    public static enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    public static int time = 0;

    public static void dfs(List<Node> graph) {
        graph.forEach(node -> {
            if (node.color.equals(WHITE)) {
                visit(node);
            }
        });
    }

    private static void visit(Node node) {
        time += 1;
        node.startTime = time;
        node.color = GRAY;
        node.neighbors.forEach(neighbor -> {
            if (neighbor.color.equals(WHITE)) {
                neighbor.pre = node;
                visit(neighbor);
            }
        });
        node.color = BLACK;
        time += 1;
        node.endTime = time;
        System.out.println("Node " + node.name + ", start:" + node.startTime + " end:" + node.endTime);
    }

    public static void main(String[] args) {
        Node u = new Node("u");
        Node v = new Node("v");
        Node w = new Node("w");
        Node x = new Node("x");
        Node y = new Node("y");
        Node z = new Node("z");
        u.neighbors.add(v);
        u.neighbors.add(x);

        v.neighbors.add(y);

        w.neighbors.add(y);
        w.neighbors.add(z);

        x.neighbors.add(v);

        y.neighbors.add(x);

        z.neighbors.add(z);
        List<Node> graph = Arrays.asList(u, v, w, x, y, z);
        dfs(graph);
    }
}
