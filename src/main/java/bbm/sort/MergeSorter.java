package bbm.sort;

/**
 * 利用分治思想，将数组平均地划分为 2 段，然后对这两段递归地再向下划分，直到每段的长度都为 1 时（长度为 1 意味着已经有序），开始进行合并
 * 合并过程就是不断地从要合并的两段（有序）数组中找最小的数，然后放入一个新的排好序的数组中
 *
 * 时间复杂度: O(n*log(n))
 * 空间复杂度: O(n)
 *
 * @author bbm
 */
public class MergeSorter implements Sorter {
    @Override
    public int[] sort(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length < 2) {
            return nums;
        }
        return sort(nums, 0, nums.length);
    }

    private int[] sort(int[] nums, int p, int r) {
        if (p < r) {
            if (r - p >= 2) {
                // 当前待处理的数组长度大于 2，进行递归拆分
                int q = (p + r) / 2;
                sort(nums, p, q);
                sort(nums, q, r);
                merge(nums, p, q, r);
            } else {
                // 当前待处理的数组长度等于 2，直接进行合并
                if (nums[p] > nums[r - 1]) {
                    int tmp = nums[r - 1];
                    nums[r - 1] = nums[p];
                    nums[p] = tmp;
                }
            }
        }
        return nums;
    }

    private void merge(int[] nums, int p, int q, int r) {
        // 复制左右两个数组，复制完成后，nums 的 [p, r) 段就作为合并之后的 result 数组
        int[] leftArray = subArray(nums, p, q);
        int[] rightArray = subArray(nums, q, r);
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = p; i < r; i++) {
            if (leftArray.length <= leftIndex) {
                // 左数组已经为空，将右数组的数据按序添加到 result 中
                nums[i] = rightArray[rightIndex];
                rightIndex++;
            } else if (rightArray.length <= rightIndex) {
                // 右数组已经为空，将左数组的数据按序添加到 result 中
                nums[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (leftArray[leftIndex] < rightArray[rightIndex]) {
                // 左数组当前处理的数较小，将这个较小的数据添加到 result 中
                nums[i] = leftArray[leftIndex];
                leftIndex++;
            } else {
                // 右数组当前处理的数较小，将这个较小的数据添加到 result 中
                nums[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    private int[] subArray(int[] nums, int p, int q) {
        int[] result = new int[q - p];
        for (int i = 0; p < q; i++) {
            result[i] = nums[p];
            p++;
        }
        return result;
    }
}
