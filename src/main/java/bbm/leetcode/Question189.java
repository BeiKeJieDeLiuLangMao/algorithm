package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-array
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question189 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5, 6};
        new Question189().rotate(nums, 2);
        print(nums);
    }

    public void rotate(int[] nums, int k) {
        if (k == 0 || nums.length == 1 || nums.length == k) {
            return;
        }
        if (k > nums.length) {
            k = k % nums.length;
        }
        int originalIndex = 0;
        int originalData = nums[originalIndex];
        int handledCount = 0;
        while (handledCount < nums.length) {
            int handledIndex = originalIndex;
            int dataFrom = originalIndex - k;
            while (originalIndex != dataFrom) {
                if (dataFrom < 0) {
                    dataFrom += nums.length;
                }
                nums[handledIndex] = nums[dataFrom];
                handledCount++;
                handledIndex = dataFrom;
                dataFrom = dataFrom - k;
            }
            nums[handledIndex] = originalData;
            handledCount++;
            if (handledCount < nums.length) {
                originalIndex += 1;
                originalData = nums[originalIndex];
            }
        }
    }
}
