package bbm.leetcode.question;

import bbm.leetcode.common.ListNode;
import java.util.HashSet;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 *
 * @author bbm
 * @date 2020/5/21
 */
public class Question141 {
    public static void main(String[] args) {
        System.out.println(new Question141().hasCycle2(ListNode.buildCycle(new Integer[] {3, 2, 0, -4}, 1)));
    }

    /**
     * hash 解法
     */
    public boolean hasCycle(ListNode node) {
        HashSet<ListNode> set = new HashSet<>();
        while (node != null) {
            set.add(node);
            if (set.contains(node.next)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 利用快慢指针的思路，类似于两个人在一个跑道跑步，一个人跑得快一个人跑得慢，它们必定再次相遇
     */
    public boolean hasCycle2(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && slow != null) {
            slow = slow.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
