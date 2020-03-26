package bbm.sort;

/**
 * 计数排序不需要进行数组数据的大小比较，在使用该排序方法时，我们需要知道数组 nums 的数据范围，本例中我们假设-50000 <= nums[i] <= 50000
 * 然后我们需要创建一个保存临时数据的数组 counts，保证对任意下标 x 都有 nums[x] = i, counts[i] = <nums 中小于 i 的元素的个数>
 * 然后，我们扫描待排序的数组，根据该数 i 在 counts 数组中的记录，可以得到 nums 中小于 i 的元素个数，也就得到 i 所处的正确位置
 *
 * 时间复杂度: O(n + k)
 * 空间复杂度: O(n + k) 能优化到 O(k)
 *
 * 我觉得在 counts 中没必要记录小于 i 的元素个数，只需要记录等于 i 的元素个数就够了，这时候我们只需要从左到右遍历 counts
 * 每当 number = counts[index] > 0 时，只需要将 number 个 index 推入 result 数组即可，理论上它和计数排序效率差不多
 * 具体参考 {@link CountingSorter#countingSort(int, int[])}
 *
 * @author bbm
 */
public class CountingSorter implements Sorter {

    @Override
    public int[] sort(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 1) {
            return nums;
        }
        int[] counts = new int[100000];
        for (int data : nums) {
            counts[data + 50000] += 1;
        }
        return countingSort(nums, counts);
    }

    private int[] countingSort(int[] nums, int[] counts) {
        int[] result = new int[nums.length];
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i - 1] + counts[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[counts[nums[i] + 50000] - 1] = nums[i];
            counts[nums[i] + 50000] -= 1;
        }
        return result;
    }

    private int[] countingSort(int size, int[] counts) {
        int[] result = new int[size];
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    result[index++] = i - 50000;
                }
            }
        }
        return result;
    }
}
