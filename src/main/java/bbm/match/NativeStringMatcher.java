package bbm.match;

import java.util.LinkedList;
import java.util.List;

/**
 * 遍历字符串，以每一位作为开头检查是否和目标字符串相同
 *
 * 时间复杂度：O((n-m+1)*m)
 *
 * @author bbm
 */
public class NativeStringMatcher implements StringMatcher {
    @Override
    public List<Integer> match(String string, String pattern) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < string.length() - pattern.length() + 1; i++) {
            boolean match = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) != string.charAt(i + j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.add(i);
            }
        }
        return result;
    }
}
