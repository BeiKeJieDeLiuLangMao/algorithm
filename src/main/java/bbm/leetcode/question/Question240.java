package bbm.leetcode.question;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 *
 * @author bbm
 * @date 30/5/2020
 */
public class Question240 {
    public static void main(String[] args) {
        System.out.println(new Question240().searchMatrix(new int[][] {
            new int[] {1, 3, 5, 7, 9},
            new int[] {2, 4, 6, 8, 10},
            new int[] {11, 13, 15, 17, 19},
            new int[] {12, 14, 16, 18, 20},
            new int[] {21, 22, 23, 24, 25}

        }, 13));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return binarySearchIn2Way(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    public boolean binarySearchIn2Way(int[][] matrix, int target,
        int rowStart, int rowEnd,
        int colStart, int colEnd) {
        while (rowStart <= rowEnd && colStart <= colEnd) {
            boolean shift = false;
            for (int row = rowStart; row <= rowEnd; row++) {
                if (matrix[row][colStart] == target) {
                    return true;
                } else if (matrix[row][colStart] > target) {
                    rowEnd = row - 1;
                    shift = true;
                    break;
                }
            }
            for (int row = rowStart; row <= rowEnd; row++) {
                if (matrix[row][colEnd] == target) {
                    return true;
                } else if (matrix[row][colEnd] > target) {
                    rowStart = row;
                    shift = true;
                    break;
                }
            }
            if (!shift) {
                return false;
            }
            shift = false;
            for (int col = colStart; col <= colEnd; col++) {
                if (matrix[rowStart][col] == target) {
                    return true;
                } else if (matrix[rowStart][col] > target) {
                    colEnd = col - 1;
                    shift = true;
                    break;
                }
            }
            for (int col = colStart; col <= colEnd; col++) {
                if (matrix[rowEnd][col] == target) {
                    return true;
                } else if (matrix[rowEnd][col] > target) {
                    colStart = col;
                    shift = true;
                    break;
                }
            }
            if (!shift) {
                return false;
            }
            int rowMid = (rowStart + rowEnd) / 2;
            int colMid = (colStart + colEnd) / 2;
            int midNumber = matrix[rowMid][colMid];
            if (midNumber == target) {
                return true;
            } else if (midNumber > target) {
                return binarySearchIn2Way(matrix, target, rowStart, rowEnd, colStart, colMid - 1) ||
                    binarySearchIn2Way(matrix, target, rowStart, rowMid - 1, colStart, colEnd);
            } else {
                return binarySearchIn2Way(matrix, target, rowStart, rowEnd, colMid + 1, colEnd) ||
                    binarySearchIn2Way(matrix, target, rowMid + 1, rowEnd, colStart, colEnd);
            }
        }
        return false;
    }
}
