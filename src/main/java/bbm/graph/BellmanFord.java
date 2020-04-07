package bbm.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 解决单源最短路径问题, 本例中使用的 Bellman-Ford 算法能够处理有负权重的问题，它的思想是通过对每一条边进行松弛化，重复遍历 V-1 次所有边
 * (V = 节点数)。
 * 这里的松弛化过程就是：我们用 d 来描述从源点出发到某一节点的最短路径估计，当我们要对一条 s->v 的边进行松弛化时，我们需要
 * 比较 v.d 和 s.d + w<s, v>，如果 v.d 更大则说明如果想要到哒 v 通过 s 再到 v 能更短，这时 s 将成为 v 的前缀路径，v 的 d 修改为
 * s.d + w<s, v>
 * 因为每次对所有边进行松弛化后，总会有至少一个节点的 d 达到最优值，所以重复遍历 V-1 次所有边之后，就能得到最终结果
 *
 * @author bbm
 */
public class BellmanFord {

    public static class Node {
        String name;
        int d = 10000;
        Node pre = null;

        public Node(String name) {
            this.name = name;
        }
    }

    public static class Line {
        int w;
        Node[] points;

        public Line(Node node1, Node node2, int w) {
            this.points = new Node[] {node1, node2};
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
        Node z = new Node("z");
        Node y = new Node("y");
        List<Node> nodes = Arrays.asList(s, t, x, z, y);
        List<Line> lines = new LinkedList<>();
        lines.add(new Line(s, t, 6));
        lines.add(new Line(s, y, 7));
        lines.add(new Line(y, y, 8));
        lines.add(new Line(z, s, 2));
        lines.add(new Line(t, x, 5));
        lines.add(new Line(x, t, -2));
        lines.add(new Line(t, z, -4));
        lines.add(new Line(y, x, -3));
        lines.add(new Line(y, z, 9));
        lines.add(new Line(z, x, 7));

        s.d = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Line line : lines) {
                // 松弛化过程
                relax(line.points[0], line.points[1], line.w);
            }
        }
        for (Line line : lines) {
            if (line.points[1].d > line.points[0].d + line.w) {
                System.out.println("Has cycle");
            }
        }
        for (Node node : nodes) {
            System.out.println(node.name + ":" + node.d + "   pre:" + (node.pre != null ? node.pre.name : ""));
        }
    }
}
