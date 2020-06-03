package bbm.leetcode;

/**
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。、
 *
 * 示例：
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *
 * 注意：
 *
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *  
 *
 * 进阶：
 * 尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-2d-vector
 *
 * @author bbm
 * @date 2020/6/3
 */
public class Question251 {
    public static void main(String[] args) {
        Vector2D obj = new Vector2D(new int[][] {
            new int[] {1, 2},
            new int[] {3},
            new int[] {4}
        });
        while (obj.hasNext()) {
            System.out.println(obj.next());
        }
    }

    static class Vector2D {

        private int[][] v;
        private int row;
        private int col = -1;

        public Vector2D(int[][] v) {
            this.v = v;
            fixRolCol();
        }

        public int next() {
            int result = v[row][col];
            fixRolCol();
            return result;
        }

        private void fixRolCol() {
            col++;
            while (row < v.length && col <= v[row].length) {
                if (col == v[row].length) {
                    col = 0;
                    row++;
                } else {
                    break;
                }
            }
        }

        public boolean hasNext() {
            return row < v.length && col < v[row].length;
        }
    }
}
