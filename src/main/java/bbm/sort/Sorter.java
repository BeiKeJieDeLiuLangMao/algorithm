package bbm.sort;

/**
 * @author Chen Yang/CL10060-N/chen.yang@linecorp.com
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
            System.out.print(num);
        }
        System.out.println();
    }
}
