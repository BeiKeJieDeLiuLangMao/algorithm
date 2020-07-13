package bbm.leetcode.bytedance.array;

import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1018 {
    public static void main(String[] args) {
        System.out.println(new Array1018().findKthLargest2(new int[] {3, 2, 1, 5, 6, 4}, 2));
    }

    /**
     * 使用了最小堆，堆内保存了 k 个元素，最后输出队首
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 利用快排的思想，每次找到 pivot 应该所处的 index，如果和我们想要找到 index 相同则返回，否则根据 pivotIndex 和 wantedIndex 的关系，在左半边和右半边找
     */
    public int findKthLargest2(int[] nums, int k) {
        int wantIndex = nums.length - k;
        return partition(nums, 0, nums.length - 1, wantIndex);
    }

    private int partition(int[] nums, int begin, int end, int wantIndex) {
        int pivot = nums[end];
        int leftEnd = begin - 1;
        for (int i = begin; i < end; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[leftEnd + 1];
                nums[leftEnd + 1] = nums[i];
                nums[i] = temp;
                leftEnd++;
            }
        }
        leftEnd++;
        if (leftEnd == wantIndex) {
            return pivot;
        } else if (leftEnd < wantIndex) {
            return partition(nums, leftEnd, end - 1, wantIndex - 1);
        } else {
            return partition(nums, begin, leftEnd - 1, wantIndex);
        }
    }
}
