package bbm.leetcode.question;

import static bbm.leetcode.common.Utils.print;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question88 {
    public static void main(String[] args) {
        int[] nums = new int[] {0};
        new Question88().merge(nums, 0, new int[] {1}, 1);
        print(nums);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mergedEnd = m + n - 1;
        int nums1End = m - 1;
        int nums2End = n - 1;
        while (nums2End >= 0 && nums1End >= 0) {
            if (nums2[nums2End] >= nums1[nums1End]) {
                nums1[mergedEnd] = nums2[nums2End];
                mergedEnd--;
                nums2End--;
            } else {
                nums1[mergedEnd] = nums1[nums1End];
                mergedEnd--;
                nums1End--;
            }
        }
        if (nums2End >= 0) {
            if (nums2End + 1 >= 0) {
                System.arraycopy(nums2, 0, nums1, 0, nums2End + 1);
            }
        }
    }
}
