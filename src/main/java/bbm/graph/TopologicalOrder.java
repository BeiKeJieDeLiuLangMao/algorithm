package bbm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bbm.graph.TopologicalOrder.Color.GRAY;
import static bbm.graph.TopologicalOrder.Color.WHITE;

/**
 * 当且仅当图中没有定向环时（即有向无环图），才有可能进行拓扑排序。
 *
 * 在图论中，由一个有向无环图的顶点组成的序列，当且仅当满足下列条件时，才能称为该图的一个拓扑排序（英语：Topological sorting）：
 * 1. 序列中包含每个顶点，且每个顶点只出现一次；
 * 2. 若A在序列中排在B的前面，则在图中不存在从B到A的路径。
 *
 *
 * @author bbm
 */
public class TopologicalOrder {
    public static class Node {
        String name;
        Color color = WHITE;
        int startTime;
        int endTime;
        Node pre = null;
        List<Node> next = new LinkedList<>();

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
        node.next.forEach(neighbor -> {
            if (neighbor.color.equals(WHITE)) {
                neighbor.pre = node;
                visit(neighbor);
            }
        });
        node.color = Color.BLACK;
        time += 1;
        node.endTime = time;
    }

    public static void topologicalSort(List<Node> nodes) {
        dfs(nodes);
        nodes.sort((o1, o2) -> o2.endTime - o1.endTime);
        for (Node node : nodes) {
            System.out.print(node.name + " ");
        }
    }

    public static void main(String[] args) {
        Node w = new Node("袜子");
        Node n = new Node("内裤");
        Node k = new Node("裤子");
        Node x = new Node("鞋子");
        Node s = new Node("手表");
        Node c = new Node("衬衣");
        Node y = new Node("腰带");
        Node l = new Node("领带");
        Node j = new Node("夹克");
        w.next.add(x);
        n.next.addAll(Arrays.asList(k, x));
        k.next.addAll(Arrays.asList(x, y));
        c.next.addAll(Arrays.asList(y, l));
        y.next.add(j);
        l.next.add(j);
        List<Node> nodes = Arrays.asList(w, n, k, x, s, c, y, l, j);
        topologicalSort(nodes);
    }
}
