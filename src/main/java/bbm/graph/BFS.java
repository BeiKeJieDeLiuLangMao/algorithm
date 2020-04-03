package bbm.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static bbm.graph.BFS.Color.BLACK;
import static bbm.graph.BFS.Color.GRAY;
import static bbm.graph.BFS.Color.WHITE;

/**
 * 图的广度优先搜索，以链表的形式表述邻接关系, 这里我们利用队列来实现 BFS
 *
 * BFS 可以用来计算最短路径
 *
 * @author bbm
 */
public class BFS {

    public static class Node {
        String name;
        Color color = WHITE;
        int d = Integer.MAX_VALUE;
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

    public static void bfs(Node node) {
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        node.color = GRAY;
        node.d = 0;
        node.pre = null;
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            current.neighbors.forEach(neighbor -> {
                if (neighbor.color.equals(WHITE)) {
                    neighbor.color = GRAY;
                    neighbor.d = current.d + 1;
                    neighbor.pre = current;
                    queue.offer(neighbor);
                }
            });
            current.color = BLACK;
            System.out.println("Node " + current.name + ", d: " + current.d);
        }
    }

    public static void main(String[] args) {
        Node s = new Node("s");
        Node r = new Node("r");
        Node v = new Node("v");
        Node w = new Node("w");
        Node t = new Node("t");
        Node x = new Node("x");
        Node y = new Node("y");
        Node u = new Node("u");
        s.neighbors.add(w);
        s.neighbors.add(r);

        r.neighbors.add(s);
        r.neighbors.add(v);

        v.neighbors.add(r);

        w.neighbors.add(s);
        w.neighbors.add(t);
        w.neighbors.add(x);

        t.neighbors.add(u);
        t.neighbors.add(x);
        t.neighbors.add(w);

        x.neighbors.add(w);
        x.neighbors.add(t);
        x.neighbors.add(y);
        x.neighbors.add(u);

        y.neighbors.add(x);
        y.neighbors.add(u);

        u.neighbors.add(t);
        u.neighbors.add(x);
        u.neighbors.add(y);
        bfs(s);
        System.out.println("Path from s to y:");
        printPath(s, y);
        System.out.println();
    }

    private static void printPath(Node s, Node y) {
        if (s == y) {
            System.out.print(s.name + " ");
        } else {
            if (y.pre == null) {
                System.out.println("No path from " + s.name + " to " + y.name);
            } else {
                printPath(s, y.pre);
                System.out.print(y.name + " ");
            }
        }
    }
}
