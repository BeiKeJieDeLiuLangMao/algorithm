package bbm.leetcode;

import java.util.BitSet;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 *
 * @author bbm
 * @date 2020/6/2
 */
public class Question79 {
    public static void main(String[] args) {
        System.out.println(new Question79().exist(new char[][] {
            new char[] {'A', 'B', 'C', 'E'},
            new char[] {'S', 'F', 'C', 'S'},
            new char[] {'A', 'D', 'E', 'E'},
        }, "ABCB"));
    }

    public boolean exist(char[][] board, String word) {
        BitSet[] used = new BitSet[board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    setUsed(i, j, used);
                    if (tryFindWord(board, i, j, word, 1, used)) {
                        return true;
                    }
                    clearUsed(i, j, used);
                }
            }
        }
        return false;
    }

    private boolean tryFindWord(char[][] board, int i, int j, String word, int wordIndex, BitSet[] used) {
        if (wordIndex >= word.length()) {
            return true;
        }
        if (i - 1 >= 0 && board[i - 1][j] == word.charAt(wordIndex) && checkNotUsed(i - 1, j, used)) {
            setUsed(i - 1, j, used);
            if (tryFindWord(board, i - 1, j, word, wordIndex + 1, used)) {
                return true;
            } else {
                clearUsed(i - 1, j, used);
            }
        }
        if (i + 1 < board.length && board[i + 1][j] == word.charAt(wordIndex) && checkNotUsed(i + 1, j, used)) {
            setUsed(i + 1, j, used);
            if (tryFindWord(board, i + 1, j, word, wordIndex + 1, used)) {
                return true;
            } else {
                clearUsed(i + 1, j, used);
            }
        }
        if (j - 1 >= 0 && board[i][j - 1] == word.charAt(wordIndex) && checkNotUsed(i, j - 1, used)) {
            setUsed(i, j - 1, used);
            if (tryFindWord(board, i, j - 1, word, wordIndex + 1, used)) {
                return true;
            } else {
                clearUsed(i, j - 1, used);
            }
        }
        if (j + 1 < board[i].length && board[i][j + 1] == word.charAt(wordIndex) && checkNotUsed(i, j + 1, used)) {
            setUsed(i, j + 1, used);
            if (tryFindWord(board, i, j + 1, word, wordIndex + 1, used)) {
                return true;
            } else {
                clearUsed(i, j + 1, used);
            }
        }
        return false;
    }

    private boolean checkNotUsed(int i, int j, BitSet[] used) {
        if (used[i] == null) {
            used[i] = new BitSet();
        }
        return !used[i].get(j);
    }

    private void setUsed(int i, int j, BitSet[] used) {
        if (used[i] == null) {
            used[i] = new BitSet();
        }
        used[i].set(j);
    }

    private void clearUsed(int i, int j, BitSet[] used) {
        if (used[i] == null) {
            used[i] = new BitSet();
        }
        used[i].clear(j);
    }
}
