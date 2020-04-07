package bbm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bbm.graph.ShortestPathWithoutCycle.Color.GRAY;
import static bbm.graph.ShortestPathWithoutCycle.Color.WHITE;

/**
 * 在无环图中，我们可以按照拓扑排序的节点顺序，依次处理各个节点的所有边，当处理完最后一个节点时，整个图中的所有节点的最短路径就确定下来了
 *
 * 由于拓扑排序的性质，当在图中不存在从 B 到 A 的路径，则 A 在序列中排在 B 的前面，每当我们按照拓扑排序处理一个节点的所有边时，就会有至少一个
 * 节点的最短路径确定下来
 *
 * @author bbm
 */
public class ShortestPathWithoutCycle {
    public static class Node {
        String name;
        Color color = WHITE;
        int startTime;
        int endTime;
        Node pre = null;
        int d = 10000;
        List<Next> next = new LinkedList<>();

        public Node(String name) {
            this.name = name;
        }
    }

    public static class Next {
        Node node;
        int w;

        public Next(Node node, int w) {
            this.node = node;
            this.w = w;
        }
    }

    public enum Color {
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
        node.next.forEach(next -> {
            if (next.node.color.equals(WHITE)) {
                visit(next.node);
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

    private static void relax(Node s, Node v, int w) {
        if (v.d > s.d + w) {
            v.d = s.d + w;
            v.pre = s;
        }
    }

    public static void main(String[] args) {
        Node s = new Node("s");
        Node r = new Node("r");
        Node t = new Node("t");
        Node x = new Node("x");
        Node y = new Node("y");
        Node z = new Node("z");
        r.next.add(new Next(s, 5));
        r.next.add(new Next(t, 3));
        s.next.add(new Next(t, 2));
        s.next.add(new Next(x, 6));
        t.next.add(new Next(x, 7));
        t.next.add(new Next(y, 4));
        t.next.add(new Next(z, 2));
        x.next.add(new Next(z, 1));
        x.next.add(new Next(y, -1));
        y.next.add(new Next(z, -2));
        List<Node> nodes = Arrays.asList(s, r, z, y, x, t);
        topologicalSort(nodes);
        System.out.println();
        s.d = 0;
        for (Node node : nodes) {
            if (!node.next.isEmpty()) {
                for (Next next : node.next) {
                    relax(node, next.node, next.w);
                }
            }
        }
        for (Node node : nodes) {
            System.out.println(node.name + ":" + node.d + "   pre:" + (node.pre != null ? node.pre.name : ""));
        }
    }
}
