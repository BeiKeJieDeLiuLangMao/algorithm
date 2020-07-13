package bbm.leetcode.bytedance.dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Dp1042 {
    public static void main(String[] args) {
        System.out.println(new Dp1042().maxProfit(new int[] {3, 2, 6, 5, 0, 3}));
    }

    /**
     * 只能买卖一次，所以我们这里统计从左往右的最小值，同时也计算当前节点和之前节点最小值的差，这个差必然包括最大收益的方案
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int sum = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                sum = Math.max(sum, price - min);
            }
        }
        return sum;
    }
}
