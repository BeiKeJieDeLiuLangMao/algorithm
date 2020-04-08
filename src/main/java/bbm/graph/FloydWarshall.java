package bbm.graph;

/**
 * 解决所有节点对的最短路径问题，要求不存在负权重环路
 *
 * 假设图的所有节点为 1-n, 现在我们只考虑其中一个子集，1-k，这时候我们假设对于任何节点 i 和 j 它们的路径 p 属于 1-k，并且 p 为权重最小的路径
 * 本算法利用了 i 到 j 的中间节点均取自 1-(k-1) 的路径 p' 和上述路径 p 的关系来实现。现在我们有两条路径，其中 p 的中间节点来自于 1-k，
 * p' 的中间结点来自于 1-(k-1)，要认清它们之间的关系，我们需要分两种情况：
 * 1. k 不是路径 p 的中间节点，也就是说路径 p 实际上中间结点只在 1-(k-1) 中，也就是 p = p'
 * 2. k 是路径 p 的中间结点，那么路径可以分解为 i -> k -> j，其中 ik 和 kj 路径中的节点均来自于 1-(k-1) ，因为我们要求图中没有负环路，
 * 这时候 p = ik + kj
 *
 *
 * @author bbm
 */
public class FloydWarshall {

    private static void floyd(int[][] weight) {
        int[][][] d = new int[6][5][5];
        Integer[][][] pre = new Integer[6][5][5];
        d[0] = weight;
        pre[0] = new Integer[5][5];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (i == j || weight[i][j] == 10000) {
                    pre[0][i][j] = null;
                } else {
                    pre[0][i][j] = i + 1;
                }
            }
        }
        System.out.println("Pre" + 0 + ":");
        for (int i = 0; i < pre[0].length; i++) {
            for (int j = 0; j < pre[0].length; j++) {
                System.out.print(pre[0][i][j] + " ");
            }
            System.out.println();
        }
        for (int k = 1; k <= weight.length; k++) {
            d[k] = new int[5][5];
            pre[k] = new Integer[5][5];
            for (int i = 0; i < d[k].length; i++) {
                for (int j = 0; j < d[k].length; j++) {
                    d[k][i][j] = Math.min(d[k - 1][i][j], d[k - 1][i][k - 1] + d[k - 1][k - 1][j]);
                    if (d[k - 1][i][j] <= (d[k - 1][i][k - 1] + d[k - 1][k - 1][j])) {
                        pre[k][i][j] = pre[k-1][i][j];
                    } else {
                        pre[k][i][j] = pre[k-1][k-1][j];
                    }
                }
            }
            System.out.println("D" + k + ":");
            for (int i = 0; i < d[k].length; i++) {
                for (int j = 0; j < d[k].length; j++) {
                    System.out.print(d[k][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Pre" + k + ":");
            for (int i = 0; i < pre[k].length; i++) {
                for (int j = 0; j < pre[k].length; j++) {
                    System.out.print(pre[k][i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[][] weight = new int[][] {
            {0, 3, 8, 10000, -4},
            {10000, 0, 10000, 1, 7},
            {10000, 4, 0, 10000, 10000},
            {2, 10000, -5, 0, 10000},
            {10000, 10000, 10000, 6, 0}
        };
        System.out.println("D" + 0 + ":");
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
        floyd(weight);
    }
}
