package bbm.leetcode.bytedance.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Array1021 {
    private static Map<Integer, Integer> MAX_COUNT_CACHE = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new Array1021().getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        List<Integer> usable = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            usable.add(i);
        }
        return getPermutation(usable, k);
    }

    public String getPermutation(List<Integer> usable, int k) {
        if (k == 1) {
            StringBuilder builder = new StringBuilder();
            usable.forEach(builder::append);
            return builder.toString();
        } else {
            int cap = getMaxCount(usable.size() - 1);
            int multi = k / cap;
            int nextK = k % cap;
            int used;
            if (nextK == 0) {
                used = usable.remove(multi - 1);
                nextK = cap;
            } else {
                used = usable.remove(multi);
            }
            return used + getPermutation(usable, nextK);
        }
    }

    private int getMaxCount(int num) {
        if (MAX_COUNT_CACHE.containsKey(num)) {
            return MAX_COUNT_CACHE.get(num);
        }
        int count = 1;
        for (int i = 1; i <= num; i++) {
            count *= i;
            MAX_COUNT_CACHE.put(i, count);
        }
        return MAX_COUNT_CACHE.get(num);
    }
}
