package bbm.sort;

/**
 * 堆排的思想是先将数组想象成一个完全二叉树，根节点是 index=0，它的两个子节点是 index=1 和 index=2, 如下图所示
 *      0
*      /\
 *    1  2
 *   /\  /\
 *  3 4 5 6
 * 在此基础上，我们将构建最大堆，所谓最大堆的特性是：每个节点都大于它的两个子节点，也就是说根节点是最大的数
 * 建立最大堆的思路是从 length / 2 号节点开始从下往上的检查各个节点是否大于其子节点，如果发现不满足，则交换节点的数据
 * 当遍历到根节点时，就完成了最大堆的建立
 * 基于最大堆的特性，我们知道根节点一定是最大的节点，所以我们将根节点和数组最后一个节点交换，然后将最后一个节点排除
 * 并从根节点出发重新修复剩余节点，让其满足最大堆的性质，重复上述过程直到数组被排除到只剩下一个节点时，整个数据就是有序的了
 *
 * 时间复杂度: O(n*log(n))
 * 时间复杂度: O(1)
 *
 * @author bbm
 */
public class HeapSort implements Sorter {

    @Override
    public int[] sort(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 1) {
            return nums;
        }
        buildMaxHeap(nums);
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            maxHeap(nums, i, 0);
        }
    }

    private void buildMaxHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            maxHeap(nums, nums.length, i);
        }
    }

    private void maxHeap(int[] nums, int size, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest;
        if (left < size && nums[left] > nums[index]) {
            largest = left;
        } else {
            largest = index;
        }
        if (right < size && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != index) {
            int temp = nums[index];
            nums[index] = nums[largest];
            nums[largest] = temp;
            maxHeap(nums, size, largest);
        }
    }

}
