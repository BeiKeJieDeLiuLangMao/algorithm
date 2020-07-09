package bbm.leetcode.question;

import bbm.leetcode.common.Node;
import java.util.HashMap;
import java.util.Map;

import static bbm.leetcode.common.Node.print;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的 深拷贝。 
 *
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *
 * 示例 1：
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question138 {
    public static void main(String[] args) {
        print(new Question138().copyRandomList(Node.build(new Integer[][] {
            new Integer[] {7, null},
            new Integer[] {13, 0},
            new Integer[] {11, 4},
            new Integer[] {10, 2},
            new Integer[] {1, 0}
        })));
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Integer> originalNodeIndexMap = buildOriginalNodeIndexMap(head);
        Node[] newData = generateCopyData(head, originalNodeIndexMap.size());
        fixRandomPoint(head, originalNodeIndexMap, newData);
        return newData[0];
    }

    private Map<Node, Integer> buildOriginalNodeIndexMap(Node head) {
        Map<Node, Integer> originalNodeIndexMap = new HashMap<>();
        Node node = head;
        int index = 0;
        while (node != null) {
            originalNodeIndexMap.put(node, index);
            index++;
            node = node.next;
        }
        return originalNodeIndexMap;
    }

    private void fixRandomPoint(Node head, Map<Node, Integer> originalNodeIndexMap, Node[] copyData) {
        Node node = head;
        int index = 0;
        while (node != null) {
            if (node.random != null) {
                copyData[index].random = copyData[originalNodeIndexMap.get(node.random)];
            }
            index++;
            node = node.next;
        }
    }

    private Node[] generateCopyData(Node head, int size) {
        Node[] copyData = new Node[size];
        Node node = head;
        int index = -1;
        while (node != null) {
            copyData[++index] = new Node(node.val);
            if (index > 0) {
                copyData[index - 1].next = copyData[index];
            }
            node = node.next;
        }
        return copyData;
    }
}
