package bbm.leetcode;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question75 {

    public void sortColors(int[] nums) {
        int index0 = 0;
        int index2 = nums.length - 1;
        while (index0 < index2) {
            while (index0 < nums.length && nums[index0] == 0) {
                index0++;
            }
            while (index2 >= 0 && nums[index2] == 2) {
                index2--;
            }
            if (index0 < index2) {
                if (nums[index0] == 2 || nums[index2] == 0) {
                    if (nums[index2] == 0) {
                        int temp = nums[index0];
                        nums[index0] = 0;
                        nums[index2] = temp;
                    } else {
                        int temp = nums[index2];
                        nums[index2] = 2;
                        nums[index0] = temp;
                    }
                } else {
                    if (!handleMidPart(nums, index0, index2)) {
                        break;
                    }
                }
            }
        }
    }

    private boolean handleMidPart(int[] nums, int index0, int index2) {
        int end = index2;
        int start = index0;
        boolean changed = false;
        for (int i = start; i <= end;) {
            if (nums[i] == 0) {
                int temp = nums[start];
                nums[start] = 0;
                nums[i] = temp;
                start++;
                changed = true;
            } else if (nums[i] == 2) {
                int temp = nums[end];
                nums[end] = 2;
                nums[i] = temp;
                end--;
                changed = true;
            } else {
                i++;
            }
        }
        return changed;
    }

    public static void main(String[] args) {
        int[] data = new int[] {2, 0, 2, 1, 1, 0};
        new Question75().sortColors(data);
        print(data);
    }
}
