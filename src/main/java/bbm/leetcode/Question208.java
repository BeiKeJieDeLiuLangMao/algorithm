package bbm.leetcode;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        // 返回 true
        System.out.println(trie.search("apple"));
        // 返回 false
        System.out.println(trie.search("app"));
        // 返回 true
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        // 返回 true
        System.out.println(trie.search("app"));
    }

    static class Trie {

        private Node root = new Node();

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node handling = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (handling.table[index] == null) {
                    handling.table[index] = new Node();
                }
                handling = handling.table[index];
            }
            handling.tail = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node handling = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (handling.table[index] == null) {
                    return false;
                }
                handling = handling.table[index];
            }
            return handling.tail;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node handling = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (handling.table[index] == null) {
                    return false;
                }
                handling = handling.table[index];
            }
            return true;
        }

        private static class Node {
            private Node[] table = new Node[26];
            private boolean tail;
        }
    }
}
