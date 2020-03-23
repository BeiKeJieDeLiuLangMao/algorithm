package bbm.sort;

/**
 * 快排使用了分治的思想，它每次选取要处理的数组中最厚的一个元素作为主元素，然后遍历整个数组，利用下标来将数组划分成左右两个部分
 * 左部都是比主元素小的数据，右部都是比主元素大的数据，当处理完除主元素外的其他所有元素时，左部的末尾就是主元素在排好序的数组中应该处于的位置
 * 也就是说每一轮上述处理过程，都将为一个主元素找到合适的位置，这个位置就是排序完成之后该元素该在的位置，很明显我们需要把主元素放到这个位置上来
 * 然后递归的处理主元素左右两部分数组，直到左右数组的大小都为 1 为止
 * 总结成一句话就是：每轮迭代都会为一个元素找到正确的位置
 *
 * 很显然，如果数组本来是逆序的，当我们选最后一个元素作为主元素时，划分出来的左部数组为空，右部数组为 size - 1，这样的话快排会退化成 O(n^2)
 * 为了解决这个问题，我们可以引入随机化的过程，不是总选取最后一个节点作为主元素，而是随机选择一个元素，这样比较平衡
 * 详见 {@link QuickSorter#randomPartition(int[], int, int)}
 * 不过我在 LeetCode 上跑测试集发现不进行随机化反而更快，可能他们的已经足够随机化了，我们再进行随机化反而增加了消耗
 *
 * 平均时间复杂度: O(n*log(n))
 * 最差时间复杂度: O(n^2)
 * 时间复杂度: O(1)
 *
 * @author bbm
 */
public class QuickSorter implements Sorter {

    @Override
    public int[] sort(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 1) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int p, int r) {
        if (p < r) {
            int q = partition(nums, p, r);
            quickSort(nums, p, q - 1);
            quickSort(nums, q + 1, r);
        }
    }

    private int randomPartition(int[] nums, int p, int r) {
        int i = (int) (Math.random() * (r - p)) + p;
        int temp = nums[i];
        nums[i] = nums[r];
        nums[r] = temp;
        return partition(nums, p, r);
    }

    private int partition(int[] nums, int p, int r) {
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
