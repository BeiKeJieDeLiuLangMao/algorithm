package bbm.leetcode.interview;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 *
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/re-space-lcci
 *
 * @author bbm
 * @date 2020/7/9
 */
public class Interview1713 {
    public static void main(String[] args) {
        System.out.println(new Interview1713().respace(new String[] {"vprkj", "sqvuzjz", "ptkrqrkussszzprkqrjrtzzvrkrrrskkrrursqdqpp", "spqzqtrqs", "rkktkruzsjkrzqq", "rk", "k", "zkvdzqrzpkrukdqrqjzkrqrzzkkrr", "pzpstvqzrzprqkkkd", "jvutvjtktqvvdkzujkq", "r", "pspkr", "tdkkktdsrkzpzpuzvszzzzdjj", "zk", "pqkjkzpvdpktzskdkvzjkkj", "sr", "zqjkzksvkvvrsjrjkkjkztrpuzrqrqvvpkutqkrrqpzu"},
            "rkktkruzsjkrzqqzkvdzqrzpkrukdqrqjzkrqrzzkkrr"));
    }

    public int respace(String[] dictionary, String sentence) {
        ReverseDictionaryTree root = buildTrie(dictionary);
        int[] dp = new int[sentence.length() + 1];
        dp[0] = 0;
        // 每次计算 sentence 的一个前缀，直到整个 sentence 都被计算
        for (int end = 1; end <= sentence.length(); end++) {
            dp[end] = dp[end - 1] + 1;
            ReverseDictionaryTree node = root;
            // 计算当前 sentence 前缀中，最后的一个单词都能匹配到什么
            for (int begin = end; begin >= 1; begin--) {
                int lastCharIndex = sentence.charAt(begin - 1) - 'a';
                if (node.children[lastCharIndex] == null) {
                    // 字典树到叶子节点了，没有更长的单词了
                    break;
                } else if (node.children[lastCharIndex].existWord) {
                    // 每匹配到一个单词，就计算考虑当前单词的情况下结果和之前的结果哪个更小，复用最优子结构
                    dp[end] = Math.min(dp[begin - 1], dp[end]);
                }
                if (dp[end] == 0) {
                    // 已经找到了最优解，不需要继续匹配
                    break;
                }
                // 最有一个单词的长度+1
                node = node.children[lastCharIndex];
            }
        }
        return dp[sentence.length()];
    }

    private ReverseDictionaryTree buildTrie(String[] dictionary) {
        ReverseDictionaryTree root = new ReverseDictionaryTree();
        for (String s : dictionary) {
            root.insert(s);
        }
        return root;
    }

    private static class ReverseDictionaryTree {
        private ReverseDictionaryTree[] children = new ReverseDictionaryTree[26];
        private boolean existWord = false;

        private void insert(String word) {
            ReverseDictionaryTree node = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new ReverseDictionaryTree();
                }
                node = node.children[index];
            }
            node.existWord = true;
        }
    }
}
