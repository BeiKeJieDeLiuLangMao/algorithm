package bbm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bbm
 * @date 2020/5/20
 */
public class ListNode implements Utils.ValueIterator {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public Object getValue() {
        return val;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Utils.ValueIterator next() {
        return next;
    }

    public static ListNode build(Integer[] values) {
        ListNode head = null;
        ListNode node = null;
        for (int i = 0; i < values.length; i++) {
            if (head == null) {
                head = new ListNode(values[i]);
                node = head;
            } else {
                node.next = new ListNode(values[i]);
                node = node.next;
            }
        }
        return head;
    }

    public static ListNode find(ListNode head, int index) {
        ListNode node = head;
        int i = 0;
        while (node != null) {
            if (i == index) {
                return node;
            } else {
                i++;
            }
            node = node.next;
        }
        throw new RuntimeException("not found");
    }

    public static ListNode buildCycle(Integer[] values, int position) {
        ListNode head = build(values);
        if (position >= 0) {
            ListNode node = head;
            Map<Integer, ListNode> map = new HashMap<>();
            int index = 0;
            while (true) {
                map.put(index++, node);
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
            node.next = map.get(position);
        }
        return head;
    }
}
