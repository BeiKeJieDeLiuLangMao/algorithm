package bbm.leetcode.question;

/**
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 示例 1:
 *
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 * 示例 2:
 *
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 *
 * @author bbm
 * @date 2020/7/21
 */
public class Question740 {
    public static void main(String[] args) {
        System.out.println(new Question740().deleteAndEarn(new int[] {2, 2, 3, 3, 3, 4}));
    }

    /**
     * 核心思想是先对数据进行排序，然后根据排好的序提取最优子结构，就以我们的数据为例 2 2 3 3 3 4，我们先计算其中只有 2 时的最大分数，
     * 它的分数是 2 * <2的数量> = 4，接下来我们将 3 也考虑进来，因为 2 和 3 是相邻的所以当我们选择 2 时 3 就会被清除，而当我们选择 3 时 2 会被清除
     * 所以，我们需要做一定的取舍，即 Math.max(2 * <2的数量>, 3 * <3的数量>) 在我们的例子中将 3 考虑进来所能获得的最大值是 3 * 3 = 9，
     * 有了前面两个分数之后 data[2] = 4, data[3] = 9，我们就能构造最优子结构了，这里我们假设 i 为当前要添加进来的数字，比如上例中的 4，
     * data[i] = Math.max(data[i-1], data[i-2] + i * <i的数量>) 即 data[4] = Math.max(data[3], data[2] + 4 * 1) = 9
     *
     * 上述的是实现的思想中我们要计算每个值它的数量有多少个，这里为了优化这部分时间开销，下面的实现中利用了桶的思想，桶的下标就代表了上述的值 2，3，4
     * 桶内的值表示的是上述值的数量，例如 bucket[2] = 2, bucket[3] = 3, bucket[4] = 1, 接下来我们将结果集的前两位计算出来就可以开始动态规划过程了
     * 即 result[i] = Math.max(result[i-1], result[i-2] + bucket[i] * i);
     */
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }
        int[] result = new int[10001];
        result[0] = 0;
        result[1] = bucket[1];
        result[2] = Math.max(result[1], 2 * bucket[2]);
        for (int i = 3; i < bucket.length; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2] + bucket[i] * i);
        }
        return result[10000];
    }
}
