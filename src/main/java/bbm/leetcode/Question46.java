package bbm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static bbm.leetcode.Utils.print;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 *
 * @author bbm
 * @date 2020/5/27
 */
public class Question46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        dfs(nums, nums.length, 0, path, used, result);
        return result;
    }

    private void dfs(int[] nums, int length, int depth, List<Integer> path, Set<Integer> used, List<List<Integer>> result) {
        if (depth == length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i < length;i++) {
            if (!used.contains(nums[i])) {
                used.add(nums[i]);
                path.add(nums[i]);
                dfs(nums, length, depth+1, path, used, result);
                used.remove(nums[i]);
                path.remove(path.size()- 1);
            }
        }
    }

    public static void main(String[] args) {
        print(new Question46().permute(new int[] {1, 2, 3}));
    }
}
