package bbm.leetcode.offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 *
 * @author bbm
 * @date 2020/7/23
 */
public class Offer11 {

    public static void main(String[] args) {
        System.out.println(new Offer11().minArray(new int[] {1, 2, 2, 2, 0, 1, 1}));
    }

    /**
     * 思路是用两个指针一个是 left，一个是 right，每次取中间值和 right 比较大小，如果 mid > right, 说明最小点 > mid, 如果 mid < right
     * 最小值 <= mid, 如果它们相等，我们只需要把当前的 right 排除掉，然后继续查找即可
     */
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }
}
