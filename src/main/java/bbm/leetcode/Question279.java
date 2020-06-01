package bbm.leetcode;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question279 {
    public static void main(String[] args) {
        System.out.println(new Question279().numSquares(48));
    }

    public int numSquares(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            double sqrt = Math.sqrt(i);
            int sqrtRound = (int) Math.floor(sqrt);
            if (sqrtRound == sqrt) {
                memo[i] = 1;
            } else {
                memo[i] = 1 + memo[i - sqrtRound * sqrtRound];
                for (int j = sqrtRound - 1; j > 1; j--) {
                    memo[i] = Math.min(memo[i], 1 + memo[i - j * j]);
                }
            }
        }
        return memo[n];
    }

    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    public int numSquares2(int n) {
        // four-square and three-square theorems.
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }

        if (this.isSquare(n)) {
            return 1;
        }
        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i) {
            if (this.isSquare(n - i * i)) {
                return 2;
            }
        }
        // bottom case of three-square theorem.
        return 3;
    }
}
