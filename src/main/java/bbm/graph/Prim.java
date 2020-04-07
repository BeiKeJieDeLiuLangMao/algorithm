package bbm.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 最小权重生成树
 *
 * Prim算法的每一步都会为一棵生长中的树添加一条边，该树最开始只有一个顶点，然后会添加V-1个边。
 * 每次总是添加生长中的树和树中除该生长的树以外的部分形成的切分的具有最小权值的横切边(新加入的边一端在树中，一端在树外，并且按照边的权重顺序加入)。
 *
 * 时间复杂度: E + (V log V)
 * @author bbm
 */
public class Prim {
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
        Set<String> tree = new HashSet<>();
        tree.add(graphLines.get(0).pNames[0]);
        while (tree.size() < 9) {
            for (Line line: graphLines) {
                // 判断一个边是否一端在树内一端在树外
                if ((tree.contains(line.pNames[0]) && !tree.contains(line.pNames[1]))
                || ((tree.contains(line.pNames[1]) && !tree.contains(line.pNames[0])))) {
                    tree.add(line.pNames[0]);
                    tree.add(line.pNames[1]);
                    System.out.println(line.pNames[0] + "-" + line.pNames[1]);
                    graphLines.remove(line);
                    break;
                } else if (tree.contains(line.pNames[0])) {
                    // 都在树内
                    graphLines.remove(line);
                    break;
                }
            }
        }
    }
}
