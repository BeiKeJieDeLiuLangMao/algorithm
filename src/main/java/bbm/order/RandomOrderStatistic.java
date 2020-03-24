package bbm.order;

import java.util.HashSet;
import java.util.Set;

/**
 * 顺序统计量，即获取某一数组中第 N 大的数(n=3)，这并不需要对整个数组进行排序，本例中采用了随机快速排序的思想
 * 但是只会对分割出来的两部分中的一个进行递归处理
 *
 * 平均时间复杂度: O(n)
 * 最差时间复杂度: O(n ^ 2)
 *
 * @author bbm
 */
public class RandomOrderStatistic {

    private static final int n = 3;

    public int getNLargestNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        nums = new int[set.size()];
        int index = 0;
        for (int num: set) {
            nums[index++] = num;
        }
        int smallestN;
        if (nums.length - n + 1 > 0) {
            smallestN = nums.length - n + 1;
        } else {
            smallestN = nums.length;
        }
        return select(nums, 0, nums.length - 1, smallestN);
    }

    private int select(int[] nums, int p, int q, int n) {
        if (p == q) {
            return nums[p];
        }
        int r = partition(nums, p, q);
        int k = r - p + 1;
        if (n == k) {
            return nums[r];
        }
        if (n < k) {
            return select(nums, p, r - 1, n);
        } else {
            return select(nums, r + 1, q, n - k);
        }
    }

    private static int partition(int[] nums, int p, int r) {
        int pivot = nums[r];
        int leftEnd = p - 1;
        for (int rightEnd = p; rightEnd < r; rightEnd++) {
            if (nums[rightEnd] < pivot) {
                leftEnd++;
                int temp = nums[leftEnd];
                nums[leftEnd] = nums[rightEnd];
                nums[rightEnd] = temp;
            }
        }
        leftEnd++;
        int temp = nums[leftEnd];
        nums[leftEnd] = nums[r];
        nums[r] = temp;
        return leftEnd;
    }
}
