package bbm.sort;

import java.util.LinkedList;

/**
 * 前提：输入数据时均匀的独立的
 * 桶排的思想是根据数据的取值范围，将它们按照从小到大的顺序分到不同的桶中，在同一个桶内的数据再进行排序
 *
 * 平均时间复杂度: O(n + k)
 * 最差时间复杂度: O(n^2)
 * 时间复杂度: O(n + k) 能优化到 O(n)
 *
 * @author bbm
 */
public class BucketSorter implements Sorter{
    @Override
    public int[] sort(int[] nums) {
        LinkedList<Integer>[] buckets = new LinkedList[100000];
        for (int i = 0; i < nums.length; i++) {
            int bucket = nums[i]  + 50000;
            if (buckets[bucket] == null) {
                buckets[bucket] = new LinkedList<>();
            }
            buckets[bucket].add(nums[i]);
        }
        int index = 0;
        for (int i = 0; i < buckets.length; i ++) {
            if (buckets[i] != null) {
                buckets[i].sort(Integer::compareTo);
                for(int data: buckets[i]) {
                    nums[index++] = data;
                }
            }
        }
        return nums;
    }
}
