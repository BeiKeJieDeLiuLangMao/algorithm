package bbm.sort;

/**
 * 排序接口
 *
 * @author bbm
 */
public interface Sorter {
    /**
     * Sort
     *
     * @param nums source data
     * @return sorted data
     */
    int[] sort(int[] nums);

    /**
     * print data
     *
     * @param nums data
     */
    default void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
