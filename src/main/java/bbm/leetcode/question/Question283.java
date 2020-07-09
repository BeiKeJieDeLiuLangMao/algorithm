package bbm.leetcode.question;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question283 {
    public static void main(String[] args) {
        int[] param = new int[] {1, 0, 1};
        new Question283().moveZeroes2(param);
        print(param);
    }

    /**
     * 朴素
     */
    public void moveZeroes(int[] nums) {
        int end = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                if (end - i >= 0) {
                    System.arraycopy(nums, i + 1, nums, i, end - i);
                }
                nums[end] = 0;
                end--;
            }
        }
    }

    /**
     * 统计所有 0 的位置一步到位法，一边统计一遍移位
     */
    public void moveZeroes2(int[] nums) {
        int shift = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                shift++;
            } else if (shift > 0) {
                nums[i - shift] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
