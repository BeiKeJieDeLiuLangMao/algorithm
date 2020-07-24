package bbm.leetcode.question;

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divisor-game
 *
 * @author bbm
 * @date 2020/7/24
 */
public class Question1025 {
    public static void main(String[] args) {
        System.out.println(new Question1025().divisorGame(2));
    }

    /**
     * 本题是可以通过数学统计的方法来做，但是这里我们使用动态规划的思想做，我们从 1-N 来考虑每一个数，对于每一个数 n，我们要找能满足
     * 0 < x < N 且 N % x == 0 的值，在此基础上我们还要尽可能的让 N - x 即让另一个处于必败的情况，综上对于每一个 n 来说它的最优子结构
     * 是 dp[n] = (x: for(1->n) and n % x == 0) any dp[n-x] == false
     */
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N + 5];
        dp[1] = false;
        dp[2] = true;
        for (int n = 3; n <= N; n++) {
            for (int x = 1; x < n; x++) {
                if (n % x == 0 && !dp[n - x]) {
                    dp[n] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}
