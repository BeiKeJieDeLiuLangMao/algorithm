package bbm.leetcode;

import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author bbm
 * @date 2020/5/20
 */
public class Question204 {

    private static boolean[] table = new boolean[]{false, false};
    private static int timeOf2 = 2;
    private static int timeOf3 = 3;
    private static int timeOf5 = 5;
    private static int timeOf7 = 7;

    static {
        getTable(10000);
    }

    public static boolean[] getTable(int n) {
        int previous = table.length;
        if (n > table.length) {
            table = Arrays.copyOf(table, n + 1);
            Arrays.fill(table, previous, table.length, true);
        }
        while (true) {
            int num = timeOf2 * 2;
            if (num > n) {
                break;
            } else {
                table[num] = false;
                timeOf2++;
            }
        }
        while (true) {
            int num = timeOf3 * 3;
            if (num > n) {
                break;
            } else {
                table[num] = false;
                timeOf3++;
            }
        }
        while (true) {
            int num = timeOf5 * 5;
            if (num > n) {
                break;
            } else {
                table[num] = false;
                timeOf5++;
            }
        }
        while (true) {
            int num = timeOf7 * 2;
            if (num > n) {
                break;
            } else {
                table[num] = false;
                timeOf7++;
            }
        }
        for (int i = 2; i < previous; i++) {
            if (table[i]) {
                int time = 2;
                while (true) {
                    int num = i * time;
                    if (num > n) {
                        break;
                    } else {
                        table[num] = false;
                        time++;
                    }
                }
            }
        }
        return table;
    }

    public static void main(String[] args) {
        System.out.println(new Question204().countPrimes(10000));
    }

    public int countPrimes(int n) {
        boolean[] table = getTable(n);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (table[i]) {
                count++;
            }
        }
        return count;
    }

    public int countPrimes2(int n) {

        if (n < 3) {
            return 0;
        }
        boolean[] f = new boolean[n];
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}
