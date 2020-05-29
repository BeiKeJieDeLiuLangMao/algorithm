package bbm.leetcode;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 *
 * @author bbm
 * @date 2020/5/29
 */
public class Question48 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length - 1;
        int lastIndex = n;
        int start = 0;
        while (start < n) {
            for (int i = start; i < n; i++) {
                int leftTop = matrix[start][i];
                int rightTop = matrix[i][lastIndex - start];
                int rightBottom = matrix[lastIndex-start][lastIndex-i];
                int leftBottom = matrix[lastIndex-i][start];
                matrix[i][lastIndex - start] = leftTop;
                matrix[lastIndex-start][lastIndex-i] = rightTop;
                matrix[lastIndex-i][start] = rightBottom;
                matrix[start][i] = leftBottom;
            }
            start++;
            n--;
        }
    }

    public static void main(String[] args) {
        int[][] data = new int[][] {
            new int[] {5, 1, 9, 11},
            new int[] {2, 4, 8, 10},
            new int[] {13, 3, 6, 7},
            new int[] {15, 14, 12, 16}
        };
        new Question48().rotate(data);
        print(data);
    }
}
