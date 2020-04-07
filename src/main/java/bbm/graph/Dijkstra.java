package bbm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 解决非负值权重的单源最短路径
 *
 * Dijkstra 将节点分为两个集合，一个是处理完的节点集合，一个时待处理集合，每次从待处理集合中选取 d 最小的节点，然后松弛化该节点的所有边，
 * 重复该过程直到处理集合为空
 *
 * @author bbm
 */
public class Dijkstra {
    public static class Node {
        String name;
        int d = 10000;
        Node pre = null;
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

    private static void relax(Node s, Node v, int w) {
        if (v.d > s.d + w) {
            v.d = s.d + w;
            v.pre = s;
        }
    }

    public static void main(String[] args) {
        Node s = new Node("s");
        Node t = new Node("t");
        Node x = new Node("x");
        Node y = new Node("y");
        Node z = new Node("z");
        s.next.add(new Next(t, 10));
        s.next.add(new Next(y, 5));

        t.next.add(new Next(y, 2));
        t.next.add(new Next(x, 1));

        y.next.add(new Next(t, 3));
        y.next.add(new Next(x, 9));
        y.next.add(new Next(z, 2));

        x.next.add(new Next(z, 4));

        z.next.add(new Next(x, 6));
        z.next.add(new Next(s, 7));

        s.d = 0;
        List<Node> nodes = new ArrayList<>(Arrays.asList(s, t, x, y, z));
        List<Node> result = new ArrayList<>();
        while (!nodes.isEmpty()) {
            nodes.sort(Comparator.comparingInt(o -> o.d));
            Node node = nodes.remove(0);
            result.add(node);
            for (Next next: node.next) {
                relax(node, next.node, next.w);
            }
        }
        for (Node node: result) {
            System.out.println(node.name + ":" + node.d + "   pre:" + (node.pre != null ? node.pre.name : ""));
        }
    }
}
