package bbm.leetcode.bytedance.string;

import java.util.LinkedList;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 *
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 *
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 *
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 *
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 *
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 *
 * @author bbm
 * @date 2020/7/12
 */
public class String1013 {
    public static void main(String[] args) {
        System.out.println(new String1013().simplifyPath("/"));
    }

    public String simplifyPath(String path) {
        String removedDuplicate = removeDuplicate(path);
        LinkedList<String> parts = split2Parts(path, removedDuplicate);
        return buildResult(path, parts);
    }

    /**
     * 将分好的断重新组装成完整字符串
     */
    private String buildResult(String path, LinkedList<String> parts) {
        StringBuilder result = new StringBuilder(path.length() * 2);
        for (int i = 0; i < parts.size(); i++) {
            result.append('/');
            result.append(parts.get(i));
        }
        return result.length() == 0 ? "/" : result.toString();
    }

    /**
     * 用反斜线把字符串分段，如果发现 .. 就把上一层目录删掉，这里使用了链表，快速删除上一层目录
     */
    private LinkedList<String> split2Parts(String path, String removedDuplicate) {
        LinkedList<String> parts = new LinkedList<>();
        int begin = 0;
        int previousIndex = 0;
        while (begin < path.length()) {
            int index = removedDuplicate.indexOf('/', begin);
            boolean finish = false;
            String part = null;
            if (index > previousIndex) {
                part = removedDuplicate.substring(previousIndex + 1, index);
            } else if (index < 0) {
                if (previousIndex + 1 < removedDuplicate.length()) {
                    part = removedDuplicate.substring(previousIndex + 1);
                }
                finish = true;
            }
            if (part != null && !part.equals(".")) {
                if (part.equals("..")) {
                    if (parts.size() > 0) {
                        parts.remove(parts.size() - 1);
                    }
                } else {
                    parts.add(part);
                }
            }
            if (finish) {
                break;
            }
            previousIndex = index;
            begin = index + 1;
        }
        return parts;
    }

    /**
     * 将重复的反斜线都删除，并且把最后的反斜线也删除
     */
    private String removeDuplicate(String path) {
        String removedDuplicate;
        StringBuilder removeDuplicate = new StringBuilder(path.length() * 2);
        boolean findSplit = false;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (!findSplit && c == '/') {
                findSplit = true;
                removeDuplicate.append('/');
            } else {
                if (c != '/') {
                    removeDuplicate.append(c);
                    findSplit = false;
                }
            }
        }
        if (removeDuplicate.charAt(removeDuplicate.length() - 1) == '/') {
            removedDuplicate = removeDuplicate.substring(0, removeDuplicate.length() - 1);
        } else {
            removedDuplicate = removeDuplicate.toString();
        }
        return removedDuplicate;
    }
}
