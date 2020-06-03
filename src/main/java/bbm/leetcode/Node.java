package bbm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bbm
 * @date 2020/6/3
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static Node build(Integer[][] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        Node[] list = new Node[data.length];
        Map<Integer, Integer> randomMap = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            list[i] = new Node(data[i][0]);
            if (i > 0) {
                list[i - 1].next = list[i];
            }
            if (data[i][1] != null) {
                randomMap.put(i, data[i][1]);
            }
        }
        randomMap.forEach((index, random) -> list[index].random = list[random]);
        return list[0];
    }

    public static void print(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.println("[" + head.val + ", " +
                (head.next == null ? "null" : head.next.val) + ", " +
                (head.random == null ? "null" : head.random.val) + "]");
            head = head.next;
        }
    }
}
