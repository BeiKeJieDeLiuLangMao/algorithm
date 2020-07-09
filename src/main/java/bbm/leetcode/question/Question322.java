package bbm.leetcode.question;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 *
 * @author bbm
 * @date 2020/5/29
 */
public class Question322 {
    public static void main(String[] args) {
        System.out.println(new Question322().coinChange(new int[] {186, 419, 83, 408}, 6249));
    }

    public int coinChange(int[] coins, int amount) {
        /*Arrays.sort(coins);
        if (amount == 0) {
            return 0;
        }
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 1; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = -1;
            }
        }
        for (int usedCoin = 1; usedCoin < coins.length; usedCoin++) {
            for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
                int time = currentAmount / coins[usedCoin];
                if (currentAmount % coins[usedCoin] == 0) {
                    dp[usedCoin][currentAmount] = time;
                } else {
                    boolean find = false;
                    for (int k = time; k >= 0; k--) {
                        int leftAmount = currentAmount - k * coins[usedCoin];
                        if (dp[usedCoin - 1][leftAmount] != -1) {
                            if (dp[usedCoin - 1][leftAmount] == 0) {
                                throw new RuntimeException("should not happen");
                            }
                            dp[usedCoin][currentAmount] = k + dp[usedCoin - 1][leftAmount];
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        dp[usedCoin][currentAmount] = -1;
                    }
                }
            }
        }
        return dp[coins.length-1][amount];*/
        // 不知道为什么错了
        return -1;
    }

    public int coinChange2(int[] coins, int amount) {
        // 自底向上的动态规划
        if (coins.length == 0) {
            return -1;
        }

        // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] memo = new int[amount + 1];
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && memo[i - coin] < min) {
                    min = memo[i - coin] + 1;
                }
            }
            // memo[i] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min);
            memo[i] = min;
        }

        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
