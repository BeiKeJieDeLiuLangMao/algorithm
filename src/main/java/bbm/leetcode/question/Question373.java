
package bbm.leetcode.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import static bbm.leetcode.common.Utils.print;

/**
 * @author bbm
 * @date 2020/7/2
 */
public class Question373 {
    public static void main(String[] args) {
        print(new Question373().kSmallestPairs(
            new int[] {1, 1, 2},
            new int[] {1, 2, 3},
            10));
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>(k);
        PriorityQueue<Position> queue = new PriorityQueue<>((o1, o2) ->
            nums1[o1.row] + nums2[o1.col] - nums1[o2.row] - nums2[o2.col]);
        Set<Position> checked = new HashSet<>();
        add2Queue(checked, queue, 0, 0);
        while (!queue.isEmpty() && result.size() < k) {
            Position position = queue.poll();
            result.add(Arrays.asList(nums1[position.row], nums2[position.col]));
            if (position.row + 1 < nums1.length) {
                add2Queue(checked, queue, position.row + 1, position.col);
            }
            if (position.col + 1 < nums2.length) {
                add2Queue(checked, queue, position.row, position.col + 1);
            }
        }
        return result;
    }

    private void add2Queue(Set<Position> checked, PriorityQueue<Position> queue, int row, int col) {
        Position position = new Position(row, col);
        if (!checked.contains(position)) {
            queue.add(position);
            checked.add(position);
        }
    }

    private static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Position) {
                return row == ((Position) o).row
                    && col == ((Position) o).col;
            }
            return false;
        }
    }
}
