package bbm.leetcode.question;

import java.util.Arrays;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question350 {
    public static void main(String[] args) {
        print(new Question350().intersect2(new int[] {-2147483648, 1, 2, 3}, new int[] {1, -2147483648, -2147483648}));
    }

    /**
     * 实际上是 hashmap 的思想只不过用桶来替换 hashmap 来提高效率
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] table = new int[100];
        int[] table2 = new int[100];
        int[] left;
        int[] right;
        if (nums1.length < nums2.length) {
            left = nums1;
            right = nums2;
        } else {
            left = nums2;
            right = nums1;
        }
        for (int item : left) {
            if (item >= 0) {
                if (item >= table.length) {
                    table = Arrays.copyOf(table, item * 10);
                }
                table[item]++;
            } else {
                item = item - Integer.MIN_VALUE;
                if (item >= table2.length) {
                    table2 = Arrays.copyOf(table2, item * 10);
                }
                table2[item]++;
            }
        }
        int[] result = new int[left.length];
        int index = 0;
        for (int value : right) {
            if (value >= 0) {
                if (value < table.length) {
                    if (table[value] > 0) {
                        table[value]--;
                        result[index++] = value;
                    }
                }
            } else {
                int item = value;
                value -= Integer.MIN_VALUE;
                if (value < table2.length) {
                    if (table2[value] > 0) {
                        table2[value]--;
                        result[index++] = item;
                    }
                }
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * 基于排序的算法
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] left;
        int[] right;
        if (nums1.length < nums2.length) {
            left = nums1;
            right = nums2;
        } else {
            left = nums2;
            right = nums1;
        }
        int[] result = new int[nums1.length];
        int index = 0;
        for (int i = 0, j = 0; i < left.length && j < right.length; ) {
            if (left[i] == right[j]) {
                result[index++] = left[i];
                i++;
                j++;
            } else if (left[i] < right[j]) {
                i++;
            } else if (left[i] > right[j]) {
                j++;
            }
        }
        return Arrays.copyOf(result, index);
    }
}
