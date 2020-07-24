package bbm.leetcode.question;

/**
 * @author bbm
 * @date 2020/7/21
 */
public class Question213 {
    public static void main(String[] args) {
        System.out.println(new Question213().rob(new int[] {1, 3, 1, 3, 100}));
    }

    /**
     * 思路是进行正向和反向两轮查找，每轮查找时都需要将末尾元素排除在外，然后取这两轮查找的结果最大值，每轮查找的过程如下：
     * data[i] = Max(data[i-1], data[i-2] + nums[i], data[i-3] + nums[i]), 这里只需要考虑到 i-3即可，因为有超过3个时，比如 4，
     * i-4 和 i 之间必将有一个可以取的中间点 i - 2, 所以它的结果等价于 data[i-2] + nums[i]
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums[0];
        } else if (nums.length < 3) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length < 4) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }
        int[] num2 = new int[nums.length - 1];
        System.arraycopy(nums, 0, num2, 0, num2.length);
        int result = rob2(num2);
        for (int left = 0, right = nums.length - 1; left < right; left++, right--) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
        System.arraycopy(nums, 0, num2, 0, num2.length);
        return Math.max(result, rob2(num2));
    }

    public int rob2(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (i >= 3) {
                result[i] = Math.max(result[i], result[i - 3] + nums[i]);
            }
            result[i] = Math.max(result[i], result[i - 2] + nums[i]);
            result[i] = Math.max(result[i], result[i - 1]);
        }
        return result[nums.length - 1];
    }
}
