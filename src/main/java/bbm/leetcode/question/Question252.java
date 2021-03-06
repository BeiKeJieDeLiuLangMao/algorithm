package bbm.leetcode.question;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms
 *
 * @author bbm
 * @date 2020/5/27
 */
public class Question252 {
    public static void main(String[] args) {
        System.out.println(new Question252().canAttendMeetings(new int[][] {new int[] {19, 20}, new int[] {1, 10}, new int[] {5, 14}}));
    }

    public boolean canAttendMeetings(int[][] intervals) {
        BitSet start = new BitSet();
        AtomicInteger startCount = new AtomicInteger();
        BitSet end = new BitSet();
        AtomicInteger endCount = new AtomicInteger();
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.stream(intervals).forEach(item -> {
            if (!start.get(item[0])) {
                start.set(item[0]);
                startCount.getAndIncrement();
            }
            if (!end.get(item[1])) {
                end.set(item[1]);
                endCount.getAndIncrement();
            }
            map.put(item[0], item[1]);
        });
        if (startCount.get() == intervals.length && endCount.get() == intervals.length) {
            for (int[] item : intervals) {
                int nextStart = start.nextSetBit(item[0] + 1);
                int nextEnd = end.nextSetBit(item[1] + 1);
                if (nextStart == -1 && nextEnd == -1) {
                    continue;
                }
                if (nextEnd != -1 && nextStart >= item[1] && nextStart != -1) {
                    if (nextStart < nextEnd && map.get(nextStart) == nextEnd) {
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
