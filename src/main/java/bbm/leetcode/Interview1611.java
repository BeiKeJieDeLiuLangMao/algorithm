package bbm.leetcode;

import static bbm.leetcode.common.Utils.print;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diving-board-lcci
 *
 * @author bbm
 * @date 2020/7/8
 */
public class Interview1611 {
    public static void main(String[] args) {
        print(new Interview1611().divingBoard(1, 2, 3));
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[] {shorter * k};
        }
        int[] resultArray = new int[k + 1];
        resultArray[0] = shorter * k;
        int index = 1;
        for (int i = k - 1; i >= 0; i--) {
            resultArray[index] = resultArray[index - 1] - shorter + longer;
            index++;
        }
        return resultArray;
    }
}
