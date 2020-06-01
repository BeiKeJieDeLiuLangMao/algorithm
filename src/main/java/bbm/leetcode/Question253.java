package bbm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms-ii
 *
 * @author bbm
 * @date 2020/5/27
 */
public class Question253 {
    public static void main(String[] args) {
        System.out.println(new Question253().minMeetingRooms(new int[][]{new int[]{0, 30}, new int[]{5, 10}, new int[]{15, 20}}));
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int leftInterval = intervals.length;
        int room = 0;
        while (leftInterval > 0) {
            room++;
            Integer end = null;
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i] != null) {
                    if (end == null) {
                        end = intervals[i][1];
                        intervals[i] = null;
                        leftInterval--;
                    } else if (intervals[i][0] >= end) {
                        end = intervals[i][1];
                        intervals[i] = null;
                        leftInterval--;
                    }
                }
            }
        }
        return room;
    }
}
