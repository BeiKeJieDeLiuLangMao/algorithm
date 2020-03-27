package bbm.dp;

/**
 * Serling 公司购买长钢条, 将其切割为短钢条出售。切割工序本身没有成本支出。公司管理层希望知道最佳的切割方案。
 * 假定我们知道 Seling 公司出售一段长度为 i 英寸的钢条的价格为P(i=1,2,⋯,单位为美元)。钢条的长度均为整英寸。下图图给出了一个价格表的样例。
 * 长度i: 1  2  3  4   5   6   7   8   9   10
 * 价格p: 1  5  8  9  10  17   17  20  24  30
 *
 * 本例采用动态规划方案，自底向下的求解各个子问题，并记录子问题的解，然后逐步的计算出原问题的解
 *
 * @author bbm
 */
public class CutRod {
    int[] price = new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    public void cutRod(int n) {
        int[] bestSum = new int[n + 1];
        int[] bestCutSize = new int[n + 1];
        bestSum[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                if (q < price[i] + bestSum[j - i]) {
                    q = price[i] + bestSum[j - i];
                    bestCutSize[j] = i;
                }
            }
            bestSum[j] = q;
        }
        print(bestSum);
        print(bestCutSize);
        while (n > 0) {
            System.out.println(bestCutSize[n]);
            n = n-bestCutSize[n];
        }
    }

    void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new CutRod().cutRod(7);
    }
}
