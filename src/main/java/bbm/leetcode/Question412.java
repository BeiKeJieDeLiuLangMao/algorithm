package bbm.leetcode;

import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.Utils.print;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 *
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 *
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *
 * 示例：
 *
 * n = 15,
 *
 * 返回:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz
 *
 * @author bbm
 * @date 2020/5/26
 */
public class Question412 {
    public List<String> fizzBuzz(int n) {
        LinkedList<String> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            boolean div3 = i % 3 == 0;
            boolean div5 = i % 5 == 0;
            if (div3 && div5) {
                result.add("FizzBuzz");
            } else if (div3) {
                result.add("Fizz");
            } else if (div5) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        print(new Question412().fizzBuzz(15));
    }
}
