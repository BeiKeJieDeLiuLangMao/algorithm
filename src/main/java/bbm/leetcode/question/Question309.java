package bbm.leetcode.question;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 *
 * @author bbm
 * @date 2020/7/12
 */
public class Question309 {
    public static void main(String[] args) {
        System.out.println(new Question309().maxProfit2(new int[] {
            6, 1, 6, 4, 3, 0, 2
        }));
    }

    /**
     * 第一思路，每次多考虑一天，如果这天卖出的话，计算一下哪天买入能够达到最大盈利
     */
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        int max = 0;
        for (int sellDate = 1; sellDate < prices.length; sellDate++) {
            dp[sellDate] = dp[sellDate - 1];
            for (int buyDate = sellDate - 1; buyDate >= 0; buyDate--) {
                int diff = prices[sellDate] - prices[buyDate];
                if (diff > 0) {
                    dp[sellDate] = Math.max(dp[sellDate], buyDate - 2 >= 0 ? diff + dp[buyDate - 2] : diff);
                    if (dp[sellDate] > max) {
                        max = dp[sellDate];
                    }
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] sellDp = new int[prices.length];
        int[] freDp = new int[prices.length];
        int[] buyDp = new int[prices.length];
        buyDp[0] = -prices[0];
        for (int date = 1; date < prices.length; date++) {
            sellDp[date] = Math.max(sellDp[date - 1], buyDp[date - 1] + prices[date]);
            buyDp[date] = Math.max(freDp[date - 1] - prices[date], buyDp[date - 1]);
            freDp[date] = Math.max(buyDp[date - 1], Math.max(sellDp[date - 1], freDp[date - 1]));
        }
        return sellDp[prices.length - 1];
    }
}
