package bbm.leetcode;

import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 *
 * @author bbm
 * @date 2020/5/28
 */
public class Question215 {

    public static void main(String[] args) {
        System.out.println(new Question215().findKthLargest(new int[] {3, 3, 3, 3, 4, 3, 3, 3, 3}, 1));
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 && k == 1) {
            return nums[0];
        }
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort(int[] nums, int start, int end, int wanted) {
        int leftEnd = start - 1;
        for (int rightEnd = start; rightEnd < end; rightEnd++) {
            if (nums[rightEnd] <= nums[end]) {
                leftEnd++;
                if (leftEnd != rightEnd) {
                    int temp = nums[rightEnd];
                    nums[rightEnd] = nums[leftEnd];
                    nums[leftEnd] = temp;
                }
            }
        }
        leftEnd++;
        if (leftEnd != end) {
            int temp = nums[end];
            nums[end] = nums[leftEnd];
            nums[leftEnd] = temp;
        }
        if (leftEnd == wanted) {
            return nums[leftEnd];
        } else if (leftEnd < wanted) {
            return quickSort(nums, leftEnd + 1, end, wanted);
        } else {
            return quickSort(nums, 0, leftEnd - 1, wanted);
        }
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }
}
