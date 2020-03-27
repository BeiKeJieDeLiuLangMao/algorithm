package bbm.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 哈夫曼编码根据字符出现的频率来规划每个字符使用什么编码，它将构建一个二叉树，出现频率越高的字符越接近树根，然后从根节点数出发，当经过左节点
 * 时记录编码值0，当经过右节点时记录编码值1，按照这样的规则，从根节点到任意实际字符节点所记录的所有编码值，就是该字符的编码，例如
 *     &
 *    /\
 *   &  c
 *  /\
 * a  b
 * 上树中， a 的编码就是 00，b 的编码是 01，c 的编码是 1
 *
 * 那么怎么构建上面这棵编码树呢，我们可以采用贪心的思路，
 * 1. 先对所有字符创建其对应的树节点，得到一个节点集合 nodes，
 * 2. 然后根据所有字符出现的频率，每次拿出节点集合 nodes 中频率最低的两个节点，为它们创建一个父节点，并将父节点放入 nodes 中
 * 3. 重复该过程直到 nodes 中只含有一个节点时，该节点就是树根 root 节点
 * @author bbm
 */
public class Huffman {

    public Node encode(char[] chars, int[] frequencies) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            nodes.add(new Node(frequencies[i], chars[i]));
        }
        nodes.sort(Comparator.comparingInt(o -> o.freq));
        while (nodes.size() > 1) {
            Node left = nodes.remove(0);
            Node right = nodes.remove(0);
            Node newNode = new Node(left.freq + right.freq, '&');
            newNode.left = left;
            newNode.right = right;
            nodes.add(newNode);
            nodes.sort(Comparator.comparingInt(o -> o.freq));
        }
        return nodes.get(0);
    }

    public static void main(String[] args) {
        Node result = new Huffman().encode(new char[] {'f', 'e', 'c', 'b', 'd', 'a'}, new int[] {5, 9, 12, 13, 16, 45});
        result.print(0);
    }

    public static class Node {
        int freq;
        char ch;
        Node left;
        Node right;

        public Node(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }

        public void print(int depth) {
            for (int i = 0; i < depth; i++) {
                System.out.print('-');
            }
            System.out.println(ch + ":" + freq);
            if (left != null) {
                left.print(depth + 1);
            }
            if (right != null) {
                right.print(depth + 1);
            }
        }
    }
}
