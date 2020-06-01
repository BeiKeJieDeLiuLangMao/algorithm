package bbm.leetcode;

import java.util.LinkedList;
import java.util.List;

import static bbm.leetcode.Utils.print;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 *
 * @author bbm
 * @date 2020/6/1
 */
public class Question78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        // empty set
        result.add(new LinkedList<>());
        for (int num : nums) {
            List<List<Integer>> partResult = new LinkedList<>();
            for (List<Integer> current: result) {
                partResult.add(new LinkedList<Integer>(current){{add(num);}});
            }
            result.addAll(partResult);
        }
        return result;
    }

    public static void main(String[] args) {
        print(new Question78().subsets(new int[] {1, 2, 3}));
    }
}
