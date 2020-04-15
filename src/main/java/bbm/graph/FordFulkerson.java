package bbm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 解决最大流问题 FordFulkerson EdmondsKarp
 *
 * 算法的思想是：
 * 1. 用一条反向的边来描述消耗掉的流容量，即 a->b (14) 消耗掉 8 的流量之后，变成两条边 a->b (6) 和 b->a (8), 通过这种反向边表示的方法
 * 我们就能够处理流量消耗多了需要撤销消耗的情况。下面有一个例子介绍。
 * 2. 利用广度优先搜索，找到一条从源出发到目标点的路径（称之为增广路径），然后选取整个路径中流量最小的边，我们假设该边的流量是 weight，
 * 接下来让整个链路上的边都消耗 weight 流量。
 * 3. 重复执行上一步，直到无法找到增广路径，这时候就得到了最大流
 *
 * 我们为什么需要反向边？
 * A --3--> B --2--> C
 * |        |        |
 * 2        3        2
 * ⬇        ⬇        ⬇
 * D --2--> E --3--> F
 * 考虑上述的输入图，最大流量路径是 A->B->C->F(2) + A->B->E->F(1) + A->D->E->F(2) 最大流是5
 * 如果我们先找到的是 A->B->E->C(3) 的话，如果不使用反向边，就找到不到其他边了，最终得到的最大流是 3，而当我们加入反向边之后，可以得到
 * A->D->E->B->C->F(2) 这条增广路径，它相当于把 B->E 的流量消耗 3 退回去了 2，然后让这部分流量转向另一条路线（B->C）流向终点 F。
 * 同时，因为B -> E 退回去了 2 流量，那么就需要有人补上这 2 流量，这时候 A->D->E 的2流量就用上，通过反向边，我们可以让一部分流量改变流向，
 * 避免了一些特殊的边消耗过多流量，而得不到最优解的情况。
 * @author bbm
 */
public class FordFulkerson {
    public static class Node {
        String name;
        Map<String, Line> froms = new HashMap<>();
        Map<String, Line> tos = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        void addTo(Node to, int weight) {
            if (!tos.containsKey(to.name)) {
                tos.put(to.name, new Line(this, to, weight));
            } else {
                tos.get(to.name).weight += weight;
            }
            to.addFrom(this, weight);
        }

        private void addFrom(Node from, int weight) {
            if (!froms.containsKey(from.name)) {
                froms.put(from.name, new Line(from, this, weight));
            } else {
                froms.get(from.name).weight += weight;
            }
        }
    }

    public static class Line {
        Node from;
        Node to;
        int weight;

        int getWeight() {
            return weight;
        }

        Line(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        void reverse(int weight) {
            if (weight > this.weight) {
                throw new RuntimeException("Reverse larger than weight, max weight: " + this.weight +" expect reverse" + weight);
            }
            this.weight -= weight;
            to.addTo(from, weight);
            if (this.weight == 0) {
                if (!(from.tos.remove(to.name).weight == weight && to.froms.remove(from.name).weight == 0)) {
                    throw new RuntimeException("Remove line failed");
                }
            }
        }
    }

    private static void findMaxStream(Node s, Node t) {
        List<Line> path;
        while ((path = findPathByBFS(s, t)).size() > 0) {
            Optional<Integer> min = path.stream().map(Line::getWeight).min(Integer::compareTo);
            if (!min.isPresent()) {
                throw new RuntimeException("Why find min number failed");
            }
            int minWeight = min.get();
            System.out.println("Path min weight:" + minWeight);
            for (Line line: path) {
                System.out.println(line.from.name + "->" + line.to.name);
                line.reverse(minWeight);
            }
        }
        int maxStream = 0;
        if (t.tos.size() > 0) {
            for (Line line: t.tos.values()) {
                maxStream += line.weight;
            }
        }
        System.out.println("Max stream is " + maxStream);
    }

    private static List<Line> findPathByBFS(Node s, Node t) {
        List<Line> path = new LinkedList<>();
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(t);
        Set<String> handled = new HashSet<>();
        boolean find = false;
        while (queue.size() > 0 && !find) {
            Node temp = queue.poll();
            handled.add(temp.name);
            if (temp.froms.size() > 0) {
                for (Line line : temp.froms.values()) {
                    if (line.from == s) {
                        System.out.println("Find a path:");
                        find = true;
                        path.add(0, line);
                        break;
                    } else {
                        if (!handled.contains(line.from.name)) {
                            path.add(0, line);
                            queue.add(line.from);
                        }
                    }
                }
            }
        }
        if (!find) {
            path.clear();
        } else {
            Line temp = path.get(0);
            int index = 0;
            while (temp.to != t) {
                for (int i = index + 1; i < path.size(); i++) {
                    if (path.get(i).from != temp.to) {
                        path.remove(i);
                        break;
                    } else {
                        temp = path.get(i);
                        index = i;
                    }
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Node s = new Node("s");
        Node v1 = new Node("v1");
        Node v2 = new Node("v2");
        Node v3 = new Node("v3");
        Node v4 = new Node("v4");
        Node t = new Node("t");
        s.addTo(v1, 16);
        s.addTo(v2, 13);
        v2.addTo(v1, 4);
        v2.addTo(v4, 14);
        v1.addTo(v3, 12);
        v3.addTo(v2, 9);
        v3.addTo(t, 20);
        v4.addTo(v3, 7);
        v4.addTo(t, 4);
        findMaxStream(s, t);
    }
}
