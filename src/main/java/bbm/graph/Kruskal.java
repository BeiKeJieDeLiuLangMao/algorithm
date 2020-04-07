package bbm.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 最小权重生成树
 *
 * 按照边的权重顺序（从小到大）将边加入生成树中，但是若加入该边会与生成树形成环(新加入的边的两个端点在同一个树中)则不加入该边。
 * 直到树中含有V-1条边为止。这些边组成的就是该图的最小生成树。
 *
 * 时间复杂度：E Log E
 *
 * @author bbm
 */
public class Kruskal {

    public static class Line {
        String[] pNames;
        int w;

        public Line(String name1, String name2, int weight) {
            pNames = new String[] {name1, name2};
            w = weight;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object w) {
            return super.equals(w);
        }
    }

    public static void main(String[] args) {
        List<Line> graphLines = new LinkedList<>();
        graphLines.add(new Line("a", "b", 4));
        graphLines.add(new Line("a", "h", 8));
        graphLines.add(new Line("b", "h", 11));
        graphLines.add(new Line("c", "b", 8));
        graphLines.add(new Line("h", "g", 1));
        graphLines.add(new Line("h", "i", 7));
        graphLines.add(new Line("i", "c", 2));
        graphLines.add(new Line("i", "g", 6));
        graphLines.add(new Line("c", "d", 7));
        graphLines.add(new Line("g", "f", 2));
        graphLines.add(new Line("c", "f", 4));
        graphLines.add(new Line("d", "f", 14));
        graphLines.add(new Line("d", "e", 9));
        graphLines.add(new Line("e", "f", 10));
        graphLines.sort(Comparator.comparingInt(o -> o.w));
        // key 表示节点名，value 表示节点所处的树的所有节点
        Map<String, Set<String>> nodeTrees = new HashMap<>();
        for (Line line : graphLines) {
            // 初始化森林
            if (!nodeTrees.containsKey(line.pNames[0])) {
                nodeTrees.put(line.pNames[0], new HashSet<>());
                nodeTrees.get(line.pNames[0]).add(line.pNames[0]);
            }
            if (!nodeTrees.containsKey(line.pNames[1])) {
                nodeTrees.put(line.pNames[1], new HashSet<>());
                nodeTrees.get(line.pNames[1]).add(line.pNames[1]);
            }
            // 如果边的两端不在同一个森林，则加入该边，并将它们的所处的树合并为一个树，同时更新树中所有节点的 nodeTrees
            if (!nodeTrees.get(line.pNames[1]).equals(nodeTrees.get(line.pNames[0]))) {
                System.out.println(line.pNames[0] + "-" + line.pNames[1]);
                nodeTrees.get(line.pNames[1]).addAll(nodeTrees.get(line.pNames[0]));
                nodeTrees.put(line.pNames[0], nodeTrees.get(line.pNames[1]));
                for(String node: nodeTrees.get(line.pNames[1])) {
                    nodeTrees.put(node, nodeTrees.get(line.pNames[1]));
                }
            }
        }
    }
}
