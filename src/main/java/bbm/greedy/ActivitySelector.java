package bbm.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动选择问题：已知一些活动的开始时间和结束时间，计算怎么安排活动能让尽可能多的活动互不重叠。
 * activity 0  1  2  3  4  5  6  7  8  9  10
 * start    1  3  0  5  3  5  6  8  8  2  12
 * end      4  5  6  7  9  9 10 11 12 14  16
 * 这里我们已经对 end time 进行了排序，而 end time 最小的值肯定会出现在最优解中，如果最优解的第一个活动 end time >= activity 0 的 end time
 * 那么我们完全可以用 activity 0 来替换最优解的第一个活动，而如果最优解的第一个活动是 end time < activity 0 的 end time，这并不会发生，因为
 * activity 0 的 end time 就是最小了。
 *
 * 基于上述规律，我们可以利用贪心算法，按照 end time 递增的顺序遍历所有 activity 如果 activity 的 start end 大于最优活动集的所有活动的 end time
 * 那么我们就将这个新活动加入到 最优活动集，重复该过程知道遍历完所有活动，我们就得到了最终的最优活动集
 * @author bbm
 */
public class ActivitySelector {
    public List<Integer> activitySelector(int[] start, int[] end) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int lastEnd = end[0];
        for (int i = 1; i < start.length; i++) {
            if (start[i] > lastEnd) {
                result.add(i);
                lastEnd = end[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new ActivitySelector().activitySelector(new int[] {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12},
            new int[] {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16});
        for (int i : result) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println();
    }
}
