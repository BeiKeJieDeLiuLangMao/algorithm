package bbm.leetcode.question;

/**
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
 *
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：salary = [4000,3000,1000,2000]
 * 输出：2500.00000
 * 解释：最低工资和最高工资分别是 1000 和 4000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
 * 示例 2：
 *
 * 输入：salary = [1000,2000,3000]
 * 输出：2000.00000
 * 解释：最低工资和最高工资分别是 1000 和 3000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
 * 示例 3：
 *
 * 输入：salary = [6000,5000,4000,3000,2000,1000]
 * 输出：3500.00000
 * 示例 4：
 *
 * 输入：salary = [8000,9000,2000,3000,6000,1000]
 * 输出：4750.00000
 *  
 *
 * 提示：
 *
 * 3 <= salary.length <= 100
 * 10^3 <= salary[i] <= 10^6
 * salary[i] 是唯一的。
 * 与真实值误差在 10^-5 以内的结果都将视为正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-salary-excluding-the-minimum-and-maximum-salary
 *
 * @author bbm
 * @date 2020/6/30
 */
public class Question1491 {
    public static void main(String[] args) {
        System.out.println(new Question1491().average(new int[] {4000, 3000, 1000, 2000}));
    }

    public double average(int[] salary) {
        int max = Math.max(salary[0], salary[1]);
        int min = Math.min(salary[0], salary[1]);
        double sum = 0;
        for (int i = 2; i < salary.length; i++) {
            if (salary[i] > max) {
                sum += max;
                max = salary[i];
            } else if (salary[i] < min) {
                sum += min;
                min = salary[i];
            } else {
                sum += salary[i];
            }
        }
        return sum / (salary.length - 2);
    }
}
