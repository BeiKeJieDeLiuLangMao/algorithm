package bbm.sort;

/**
 * 插入排序将数组分为左侧和右侧，认为左侧是排好序的数组（起初左侧数组只包含一个元素，即下标 0 元素），右侧是待处理的数组
 * 然后从下标 1 开始处理右侧数组，将其依次与左侧数组中的每个元素进行比较，从而将其插入到合适的位置
 *
 * 最坏时间复杂度: O(n^2)
 * 平均时间复杂度: O(n^2)
 * 最佳时间复杂度: O(n)
 * 空间复杂度: O(1)
 *
 * @author Chen Yang/CL10060-N/chen.yang@linecorp.com
 */
public class InsertSort implements Sorter {

    @Override
    public int[] sort(int[] nums) {
        if (nums == null) {
            return new int[0];
        }
        if (nums.length < 2) {
            return nums;
        }
        // print(nums);
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (tmp < nums[j]) {
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
            // print(nums);
        }
        return nums;
    }
}
