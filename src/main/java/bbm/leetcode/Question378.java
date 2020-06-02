package bbm.leetcode;

import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *  
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question378 {
    public static void main(String[] args) {
        System.out.println(new Question378().kthSmallest2(new int[][] {
            new int[] {1, 4, 7, 11, 15},
            new int[] {2, 5, 8, 12, 19},
            new int[] {3, 6, 9, 16, 22},
            new int[] {10, 13, 14, 17, 24},
            new int[] {18, 21, 23, 26, 30},
        }, 5));
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                queue.add(anInt);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.poll();
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix.length - 1];
        while (left < right) {
            int mid = (left + right) / 2;
            int count = searchLessEquals(matrix, mid);
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int searchLessEquals(int[][] matrix, int mid) {
        int row = matrix.length - 1;
        int col = 0;
        int result = 0;
        while (row >= 0 && col < matrix.length) {
            if (matrix[row][col] <= mid) {
                result += row + 1;
                col++;
            } else {
                row--;
            }
        }
        return result;
    }
}
