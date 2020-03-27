package bbm.dp;

import java.util.ArrayList;

/**
 * 最大公共子序列：例如，ABCD 和 ACE 这两个序列的最大公共子序列为 AC
 * 本例中采用动态规划的思想，从两个序列的头出发x[i], y[j]，如果 x[i] y[j] 相等，那么到 x[i] y[j] 的最大公共子序列就比 x[i-1] y[j-1]
 * 多 1，如果他们不相等，那么就要检查 x[i-1] y[j] 和 x[i] y[j-1] 为止哪边的子序列最大，让 x[i] y[j] 也等于该值，当 i j 达到 x y 的
 * 末尾时，我们就得到了最大公共子序列
 * @author bbm
 */
public class LongestCommonSubSequence {

    public ArrayList<Character> lcsLength(char[] x, char[] y) {
        char[][] path = new char[x.length + 1][y.length + 1];
        int[][] length = new int[x.length + 1][y.length + 1];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (x[i] == y[j]) {
                    length[i + 1][j + 1] = length[i][j] + 1;
                    path[i + 1][j + 1] = '↖';
                } else if (length[i][j + 1] > length[i + 1][j]) {
                    length[i + 1][j + 1] = length[i][j + 1];
                    path[i + 1][j + 1] = '↑';
                } else {
                    length[i + 1][j + 1] = length[i + 1][j];
                    path[i + 1][j + 1] = '←';
                }
            }
        }
        System.out.print(' ');
        System.out.print(' ');
        for (char c : y) {
            System.out.print(c);
            System.out.print(' ');
            System.out.print(' ');
        }
        System.out.println();
        for (int i = 1; i < path.length; i++) {
            System.out.print(x[i - 1]);
            System.out.print(' ');
            for (int j = 1; j < path[i].length; j++) {
                System.out.print(path[i][j]);
                System.out.print(length[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        ArrayList<Character> result = new ArrayList<>();
        getPath(result, x, path, x.length, y.length);
        return result;
    }

    private void getPath(ArrayList<Character> result, char[] x, char[][] path, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (path[i][j] == '↖') {
            getPath(result, x, path, i - 1, j - 1);
            result.add(x[i - 1]);
        } else if (path[i][j] == '↑') {
            getPath(result, x, path, i - 1, j);
        } else {
            getPath(result, x, path, i, j - 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Character> result = new LongestCommonSubSequence()
            .lcsLength(new char[] {'a', 'b', 'c', 'b', 'd', 'a', 'b'}, new char[] {'b', 'd', 'c', 'a', 'b', 'a'});
        System.out.print("LCS: ");
        for (char c : result) {
            System.out.print(c);
        }
        System.out.println();
    }

}
