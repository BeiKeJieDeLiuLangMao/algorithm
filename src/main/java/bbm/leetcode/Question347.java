package bbm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bbm.leetcode.common.Utils.print;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question347 {
    public static void main(String[] args) {
        print(new Question347().topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            Integer previous = countMap.putIfAbsent(num, 1);
            if (previous != null) {
                countMap.put(num, previous + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> countMapList = new ArrayList<>(countMap.entrySet());
        quickSort(countMapList, countMapList.size() - k, 0, countMapList.size() - 1);
        int[] result = new int[k];
        int index = 0;
        for (int i = countMapList.size() - k; i < countMapList.size(); i++) {
            result[index++] = countMapList.get(i).getKey();
        }
        return result;
    }

    private void quickSort(List<Map.Entry<Integer, Integer>> list, int k, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotValue = list.get(end).getValue();
        int leftEnd = start - 1;
        for (int i = start; i < end; i++) {
            if (list.get(i).getValue() < pivotValue) {
                switchItem(list, i, leftEnd + 1);
                leftEnd++;
            }
        }
        int rightIndex = leftEnd + 1;
        switchItem(list, rightIndex, end);
        if (rightIndex < k) {
            quickSort(list, k, rightIndex + 1, end);
        } else if (rightIndex > k) {
            quickSort(list, k, start, rightIndex - 1);
        }
    }

    private void switchItem(List<Map.Entry<Integer, Integer>> list, int i, int j) {
        Map.Entry<Integer, Integer> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
