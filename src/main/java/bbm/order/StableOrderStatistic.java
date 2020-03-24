package bbm.order;

import java.util.HashSet;
import java.util.Set;

/**
 * 在 {@link RandomOrderStatistic} 中最差时间复杂度到了 O(n^2) 这是快排中也会出现的问题，即便进行了随机化，也是可能出现该问题，
 * 所以在本例中，我们不再选用最后一个数作为主元素，而是我们指定一个特定的元素，该元素需要保持划分的平衡性，这样才能保证时间复杂度收敛与 O(n)
 *
 * 具体的做法是：
 * 将n个元素划分为n/5组
 * 寻找每组的中位数
 * 找出的中位数的中位数x, 这个中位数的性质是 有 1/4 的数小于它并且有 1/4 的数大于它，所以选用它来做主元素比较平衡
 * 使用x作为主元素执行快排的 PARTITION，计算得出 x 为第 k 小的元素
 * 如果目标值 i==k，返回x；如果i<k，在低区调用PARTITION找出第i小的元素；如果i>k，在高区调用PARTITION查找第i-k小的元素
 *
 * 时间复杂度: O(n)
 *
 * @author bbm
 */
public class StableOrderStatistic {

    private static final int n = 3;

    public int getNLargestNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        nums = new int[set.size()];
        int index = 0;
        for (int num : set) {
            nums[index++] = num;
        }
        int smallestN;
        if (nums.length - n + 1 > 0) {
            smallestN = nums.length - n + 1;
        } else {
            smallestN = nums.length;
        }
        return select(nums, 0, nums.length - 1, smallestN, findMidNumber(nums));
    }

    private int select(int[] nums, int start, int end, int smallestN, int pivotIndex) {
        if (start == end) {
            return nums[start];
        }
        int rightIndex = partition(nums, start, end, pivotIndex);
        int order = rightIndex - start + 1;
        if (smallestN == order) {
            return nums[rightIndex];
        }
        if (smallestN < order) {
            return select(nums, start, rightIndex - 1, smallestN, rightIndex - 1);
        } else {
            return select(nums, rightIndex + 1, end, smallestN - order, rightIndex + 1);
        }
    }

    private int findMidNumber(int[] nums) {
        int size = (int) Math.ceil((double) nums.length / 5);
        int[][] groupedNums = new int[size][5];
        int[] indexes = new int[size];
        for (int i = 0; i < nums.length; i++) {
            int slot = i % groupedNums.length;
            groupedNums[slot][indexes[slot]] = nums[i];
            int tmp = indexes[slot];
            for (int j = indexes[slot] - 1; j >= 0; j--) {
                if (tmp < groupedNums[slot][j]) {
                    groupedNums[slot][j + 1] = groupedNums[slot][j];
                    groupedNums[slot][j] = tmp;
                }
            }
            indexes[slot] += 1;
        }
        int[] midNumbers = new int[size];
        int index = 0;
        for (int i = 0; i < groupedNums.length; i++) {
            if (indexes[i] == 5) {
                midNumbers[index++] = groupedNums[i][3];
            } else {
                midNumbers[index++] = groupedNums[i][indexes[i] / 2];
            }
        }
        int finalMid = select(midNumbers, 0, midNumbers.length - 1, size / 2, 0);
        int finalMidIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == finalMid) {
                finalMidIndex = i;
            }
        }
        return finalMidIndex;
    }

    private static int partition(int[] nums, int p, int r, int position) {
        int temp = nums[position];
        nums[position] = nums[r];
        nums[r] = temp;
        int pivot = nums[r];
        int leftEnd = p - 1;
        for (int rightEnd = p; rightEnd < r; rightEnd++) {
            if (nums[rightEnd] < pivot) {
                leftEnd++;
                temp = nums[leftEnd];
                nums[leftEnd] = nums[rightEnd];
                nums[rightEnd] = temp;
            }
        }
        leftEnd++;
        temp = nums[leftEnd];
        nums[leftEnd] = nums[r];
        nums[r] = temp;
        return leftEnd;
    }
}
