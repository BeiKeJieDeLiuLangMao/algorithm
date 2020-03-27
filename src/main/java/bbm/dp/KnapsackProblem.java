package bbm.dp;

/**
 * 0-1背包问题：给定一组多个物品，每种物品都有自己的重量和价值，在限定的总重量/总容量内，选择其中若干个（也即每种物品可以选0个或1个），
 * 设计选择方案使得物品的总价值最高。
 *
 * 动态规划的思路是：逐渐的增加商品数量和背包容量
 * 1. 如果新增的商品重量比当前背包容量大，把新增的商品排除掉，重新算总价值
 * 2. 如果新增的商品重量比当前背包容量小，比较如下两种情况哪个总价值更高，选择总价高的那个方案：
 *      2.1 新增的商品排除掉，重新算总价值
 *      2.2 将新增商品放入包内，计算 （新商品 price + 剩余商品在剩下的容量下的最高价值）
 * @author bbm
 */
public class KnapsackProblem {

    public int findBestSolution(int[] price, int[] weight, int maxWeight) {
        int itemCount = price.length;
        int[][] memo = new int[itemCount + 1][maxWeight + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= itemCount; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weight[i - 1] > j) {
                    memo[i][j] = memo[i - 1][j];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], price[i - 1] + memo[i - 1][j - weight[i - 1]]);
                }
            }
            if (memo[i][maxWeight] > max) {
                max = memo[i][maxWeight];
            }
        }
        System.out.print("\t");
        for (int i = 0; i <= maxWeight; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < memo.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < memo[i].length; j++) {
                System.out.print(memo[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        return max;
    }

    public static void main(String[] args) {
        int maxPrice = new KnapsackProblem().findBestSolution(new int[] {1, 6, 18, 22, 28},
            new int[] {1, 2, 5, 6, 7}, 11);
        System.out.println(maxPrice);
    }
}
