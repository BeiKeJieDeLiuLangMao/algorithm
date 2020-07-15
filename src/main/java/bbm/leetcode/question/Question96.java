package bbm.leetcode.question;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 *
 * @author bbm
 * @date 2020/7/15
 */
public class Question96 {
    public static void main(String[] args) {
        System.out.println(new Question96().numTrees2(5));
    }

    /**
     * 利用递归的思想，对于每一个数字集来说，都有作为树根的可能，对于每一种情况，我们可以将剩下的节点抽象为一个子问题，
     * 考虑这个子集组成的树有多少种可能
     */
    public int numTrees(int n) {
        return numTrees(1, n);
    }

    private int numTrees(int begin, int end) {
        if (begin == end) {
            return 1;
        } else {
            int sum = 0;
            for (int i = begin; i <= end; i++) {
                if (i == begin) {
                    sum += numTrees(i + 1, end);
                } else if (i == end) {
                    sum += numTrees(begin, i - 1);
                } else {
                    sum += numTrees(begin, i - 1) * numTrees(i + 1, end);
                }
            }
            return sum;
        }
    }

    /**
     * 在原来的实现中，每一种子集我们都进行了模拟，但是实际上没有必要模拟每一种情况，因为每一个子集都是上一层问题分割成的两个有序段
     * eg: [1,2,3,4,5] 当根为 3 时子问题是 [1, 2] | [4, 5], 对于每一个有序的段，只要长度相同，那它们能够组成的二叉树数量是一样的，
     * 比如 [4,5] 就和 [1,2] 的结果一样，所以这里我们利用动态规划的思想，缓存之前的计算结果，例如 [1,2] 的结果是 2, 当我们需要 [4,5] 的结果时，
     * 就可以直接复用 [1,2] 的结果 2，因为 5-4 == 2-1
     * 综上：我们的 i 从 2 开始，逐渐往高计算 n 的结果，对于期间的每一步 i，我们都遍历 i 的任意一位作为根节点时，下层子问题的可能有多少种，
     * 最后累加起来，就是 i 这一层的最终结果
     */
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
