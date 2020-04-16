package bbm.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需要根据目标字符串构建状态机，从目标字符串中找到所有出现的字符，为每个字符进行编号，然后构建 (patternLength + 1) * 字符集数量 的状态
 * 转移表，构建状态转移表的一般思路是，假设 x 为 pattern 中的任意下标，stateMap[x][x + 1] = x+1 ，此时，我们要
 * 计算 "pattern[0, x] + y" 的后缀和 pattern[0, x+1] 的最长前缀，其中 y 恒不等于 pattern[x+1]，得到这个最大前缀 maxPrefix 之后，
 * stateMap[x][y] = maxPrefix.length
 *
 * 初始化时间复杂度：O（m*字符集数量）
 * 匹配时间复杂度：O(n)
 *
 * @author bbm
 */
public class StateMachineStringMatcher implements StringMatcher {

    private int[][] stateMap;
    private Map<Character, Integer> characters = new HashMap<>();

    public StateMachineStringMatcher(String pattern) {
        int index = 0;
        if (!pattern.isEmpty()) {
            for (int i = 0; i < pattern.length(); i++) {
                if (!characters.containsKey(pattern.charAt(i))) {
                    characters.put(pattern.charAt(i), index++);
                }
            }
            stateMap = new int[pattern.length() + 1][characters.size()];
            for (int i = 0; i < pattern.length() + 1; i++) {
                String sub = i < pattern.length() ? pattern.substring(0, i) : pattern;
                int rightIndex = -1;
                if (i < pattern.length()) {
                    rightIndex = characters.get(pattern.charAt(i));
                    stateMap[i][rightIndex] = i + 1;
                }
                for (Map.Entry<Character, Integer> entry: characters.entrySet()) {
                    if (!entry.getValue().equals(rightIndex)) {
                        for (int j = i + 1; j > 0; j--) {
                            String temp = sub + entry.getKey();
                            String prefix = j < pattern.length() ? pattern.substring(0, j) : pattern;
                            if (temp.endsWith(prefix)) {
                                stateMap[i][entry.getValue()] = Math.min(j, pattern.length());
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            stateMap = null;
        }
    }

    @Override
    public List<Integer> match(String string, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (stateMap == null) {
            for (int i = 0; i < string.length() + 1; i++) {
                result.add(i);
            }
        } else {
            int state = 0;
            for (int i = 0; i < string.length(); i++) {
                if (characters.containsKey(string.charAt(i))) {
                    state = stateMap[state][characters.get(string.charAt(i))];
                } else {
                    state = 0;
                }
                if (state == pattern.length()) {
                    result.add(i - pattern.length() + 1);
                }
            }
        }
        return result;
    }
}
