package bbm.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Johnson 算法主要用于求稀疏图上的所有节点对的最短路径。其主体思想是利用重新赋予权值的方法把一个原问题带负权的图转化为权值非负的图。
 * 然后再对每个节点使用一次 Dijkstra 算法以求出所有节点对的最短路径。
 *
 * @author bbm
 */
public class Johnson {

    private static int[] bellmanFord(int[][] newWeight) {
        int[] h = new int[newWeight.length];
        h[0] = 0;
        for (int i = 1; i < h.length; i++) {
            h[i] = 10000;
        }
        for (int k = 0; k < newWeight.length - 1; k++) {
            for (int i = 0; i < newWeight.length; i++) {
                for (int j = 0; j < newWeight.length; j++) {
                    h[j] = relax(h[j], h[i], newWeight[i][j]);
                }
            }
        }
        for (int i = 0; i < newWeight.length; i++) {
            for (int j = 0; j < newWeight.length; j++) {
                if (h[i] + newWeight[i][j] < h[j]) {
                    throw new RuntimeException("Has cycle");
                }
            }
        }
        return h;
    }

    private static int relax(int dv, int ds, int w) {
        if (dv > ds + w) {
            return ds + w;
        } else {
            return dv;
        }
    }

    private static void dijkstra(int index, int[][] weight) {
        System.out.println("Start from " + (index + 1));
        int[] length = new int[weight.length];
        length[index] = 0;
        for (int i = 0; i < length.length; i++) {
            if (i != index) {
                length[i] = 10000;
            }
        }
        Set<Integer> handled = new HashSet<>();
        while (handled.size() < weight.length) {
            int min = 10000;
            int minIndex = -1;
            for (int i = 0; i < length.length; i++) {
                if (!handled.contains(i) && length[i] < min) {
                    min = length[i];
                    minIndex = i;
                }
            }
            handled.add(minIndex);
            for (int i = 0; i < weight.length; i++) {
                if (weight[minIndex][i] != 10000 && i != minIndex) {
                    length[i] = relax(length[i], length[minIndex], weight[minIndex][i]);
                }
            }
        }
        for (int i = 0; i < length.length; i++) {
            System.out.print(length[i] + " ");
        }
        System.out.println();
    }

    private static void johnson(int[][] weight) {
        int[][] newWeight = new int[weight.length + 1][weight.length + 1];
        for (int i = 0; i < newWeight.length; i++) {
            newWeight[0][i] = 0;
        }
        for (int i = 1; i < newWeight.length; i++) {
            newWeight[i][0] = 10000;
        }
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                newWeight[i + 1][j + 1] = weight[i][j];
            }
        }
        System.out.println("New weight:");
        for (int i = 0; i < newWeight.length; i++) {
            for (int j = 0; j < newWeight.length; j++) {
                System.out.print(newWeight[i][j] + " ");
            }
            System.out.println();
        }
        int[] h = bellmanFord(newWeight);
        System.out.println("H:");
        for (int i = 0; i < h.length; i++) {
            System.out.print(h[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (weight[i][j] != 10000) {
                    weight[i][j] += h[i+1] - h[j+1];
                }
            }
        }
        System.out.println("Weight:");
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < weight.length; i++) {
            dijkstra(i, weight);
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
        System.out.println("Weight:");
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
        johnson(weight);
    }
}
