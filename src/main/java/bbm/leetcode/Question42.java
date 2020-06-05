package bbm.leetcode;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 *
 * @author bbm
 * @date 2020/6/5
 */
public class Question42 {
    public static void main(String[] args) {
        System.out.println(new Question42().trap2(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, ans = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    ans += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    ans += rightMax - height[right];
                }
                right--;
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        int[] left2right = new int[height.length];
        int leftMax = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            if (h > leftMax) {
                leftMax = h;
            } else {
                left2right[i] = leftMax - h;
            }
        }
        int[] right2left = new int[height.length];
        int rightMax = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            int h = height[i];
            if (h > rightMax) {
                rightMax = h;
            } else {
                right2left[i] = rightMax - h;
            }
        }
        int result = 0;
        for (int i = 0; i < right2left.length; i++) {
            result += Math.min(left2right[i], right2left[i]);
        }
        return result;
    }

}
