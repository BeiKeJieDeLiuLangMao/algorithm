package bbm.sort;

/**
 * 基数排序的思想是将数据按照 10 进制划分为多个位（这里我们假定最大数据的位数是 d = 5），然后从低位开始按照位大小进行排序
 * 因为在数学中, 数位越高,数位值对数的大小的影响就越大, 所以如果我们用从高位到低位排序的话，会影响高位已经排好的大小关系
 * 而从低位向高位排序的话，低位已经排好的顺序会被一直保持住
 *
 * 时间复杂度: O(n*k)
 * 空间复杂度: O(n*k) 能优化到 O(n+k)
 *
 * @author bbm
 */
public class RadixSorter implements Sorter {
    @Override
    public int[] sort(int[] nums) {
        int[][] sort = new int[20][nums.length];
        for (int i = 1; i < 6; i++) {
            int[] numbers = new int[20];
            int mod = (int) (Math.pow(10, i));
            int div = (int) (Math.pow(10, i - 1));
            for (int num : nums) {
                int digit = num % mod/ div;
                sort[digit + 10][numbers[digit + 10]] = num;
                numbers[digit + 10] += 1;
            }
            int index = 0;
            for (int k = 0; k < 20; k++) {
                for (int l = 0; l < numbers[k]; l ++) {
                    nums[index] = sort[k][l];
                    index++;
                }
            }
        }
        return nums;
    }
}
