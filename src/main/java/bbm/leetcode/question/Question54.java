package bbm.leetcode.question;

import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 *
 * @author bbm
 * @date 2020/5/29
 */
public class Question54 {

    public static void main(String[] args) {
        print(new Question54().spiralOrder(new int[][] {
            new int[] {1, 2, 3, 4},
            new int[] {5, 6, 7, 8},
            new int[] {9, 10, 11, 12},
        }));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        scan(result, matrix, row, col);
        return result;
    }

    private void scan(List<Integer> result, int[][] matrix, int row, int col) {
        int rowStart = 0;
        int colStart = 0;
        while (rowStart < row || colStart < col) {
            if (rowStart < row) {
                right(result, matrix, rowStart++, colStart, col);
            }
            if (colStart < col) {
                down(result, matrix, --col, rowStart, row);
            }
            if (rowStart < row) {
                left(result, matrix, --row, col, colStart);
            }
            if (colStart < col) {
                up(result, matrix, colStart++, row, rowStart);
            }
        }
    }

    private void right(List<Integer> result, int[][] matrix, int row, int colStart, int colEnd) {
        for (int col = colStart; col < colEnd; col++) {
            result.add(matrix[row][col]);
        }
    }

    private void down(List<Integer> result, int[][] matrix, int col, int rowStart, int rowEnd) {
        for (int row = rowStart; row < rowEnd; row++) {
            result.add(matrix[row][col]);
        }
    }

    private void left(List<Integer> result, int[][] matrix, int row, int colStart, int colEnd) {
        for (int col = colStart - 1; col >= colEnd; col--) {
            result.add(matrix[row][col]);
        }
    }

    private void up(List<Integer> result, int[][] matrix, int col, int rowStart, int rowEnd) {
        for (int row = rowStart - 1; row >= rowEnd; row--) {
            result.add(matrix[row][col]);
        }
    }
}
