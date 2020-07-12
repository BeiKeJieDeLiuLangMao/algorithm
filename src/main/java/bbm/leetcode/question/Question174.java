package bbm.leetcode.question;

import java.util.Arrays;

/**
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 *  
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *  
 *
 * 说明:
 *
 * 骑士的健康点数没有上限。
 *
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 *
 * @author bbm
 * @date 2020/7/12
 */
public class Question174 {
    public static void main(String[] args) {
        System.out.println(new Question174().calculateMinimumHP(new int[][] {
            new int[] {-2, -3, 3},
            new int[] {-5, -10, 1},
            new int[] {10, 30, -5}
        }));
    }

    /**
     * 从终点出发，向左上扫描每一个点，对于每个点，统计其向右移动和向下移动哪条路径所需的血量最少，选取最少的和当前格的消耗相减，
     * 就可以得到当前格的最低血量要求，如果相减得到的值小于等于 0，要将血量要求提升到 1（能活着走到本格），扫描结束后，
     * 起点保存的就是最低的初始血量要求
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length + 1][dungeon[0].length + 1];
        for (int i = 0; i <= dungeon.length; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[dungeon.length][dungeon[0].length - 1] = 1;
        dp[dungeon.length - 1][dungeon[0].length] = 1;
        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[i].length - 1; j >= 0; j--) {
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
