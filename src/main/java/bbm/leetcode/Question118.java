package bbm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question118 {
    public static void main(String[] args) {
        print(new Question118().generate(5));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<>(Arrays.asList(new Integer[i + 1])));
            result.get(i).set(0, 1);
            result.get(i).set(i, 1);
            for (int j = 1; j < i; j++) {
                result.get(i).set(j, result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }
        }
        return result;
    }
}
