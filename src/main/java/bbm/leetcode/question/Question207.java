package bbm.leetcode.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question207 {

    public static void main(String[] args) {
        System.out.println(new Question207().canFinish(3, new int[][] {
            new int[] {1, 0},
            new int[] {1, 2},
            new int[] {0, 1}
        }));
    }

    /**
     * 1.根据输入的课程依赖关系，转化为两个数据体:
     *      1. 保存了每门课的前置课程数
     *      2.保存了每门课的后置课程列表
     * 2. 遍历所有的课程，如果该课程 i 没有前置课程就记录该课程已完成，然后将该课程 i 的所有后置课程的前置可能数 -1，
     *      1. 如果课程 j 的前置课程数减到 0，就递归的处理课程 j，递归内容如步骤 2
     * 3. 遍历完所有课程后，如果所有可能都已完成，就返回 true，否则返回 false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] finishedTable = new boolean[numCourses];
        List<Integer>[] autoFinishTable = new List[numCourses];
        Map<Integer, Integer> waitingCourse = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int thisCourse = prerequisite[0];
            int preCourse = prerequisite[1];
            if (finishedTable[preCourse]) {
                finishCourse(thisCourse, autoFinishTable, finishedTable, waitingCourse);
            } else {
                add2AutoFinishTable(preCourse, thisCourse, autoFinishTable, waitingCourse);
            }
        }
        for (int i = 0; i < finishedTable.length; i++) {
            if (!finishedTable[i] && !waitingCourse.containsKey(i)) {
                finishCourse(i, autoFinishTable, finishedTable, waitingCourse);
            }
        }
        for (boolean finish : finishedTable) {
            if (!finish) {
                return false;
            }
        }
        return true;
    }

    private void add2AutoFinishTable(int preCourse, int afterCourse, List<Integer>[] autoFinishTable,
        Map<Integer, Integer> waitingCourse) {
        if (autoFinishTable[preCourse] == null) {
            autoFinishTable[preCourse] = new ArrayList<>();
        }
        autoFinishTable[preCourse].add(afterCourse);
        Integer previousWaitingCount = waitingCourse.putIfAbsent(afterCourse, 1);
        if (previousWaitingCount != null) {
            waitingCourse.put(afterCourse, previousWaitingCount + 1);
        }

    }

    private void finishCourse(int course, List<Integer>[] autoFinishTable, boolean[] finishedTable,
        Map<Integer, Integer> waitingCourse) {
        finishedTable[course] = true;
        if (autoFinishTable[course] != null) {
            Queue<Integer> taskList = new LinkedList<>();
            taskList.add(course);
            while (!taskList.isEmpty()) {
                Integer handlingCourse = taskList.poll();
                List<Integer> autoFinishList = autoFinishTable[handlingCourse];
                if (autoFinishList != null) {
                    for (Integer autoFinish : autoFinishList) {
                        Integer previousWaitingCount = waitingCourse.get(autoFinish);
                        if (previousWaitingCount == 1) {
                            waitingCourse.remove(autoFinish);
                            finishedTable[autoFinish] = true;
                            if (autoFinishTable[autoFinish] != null) {
                                taskList.add(autoFinish);
                            }
                        } else {
                            waitingCourse.put(autoFinish, previousWaitingCount - 1);
                        }
                    }
                    autoFinishTable[handlingCourse] = null;
                }
            }
        }
    }
}
