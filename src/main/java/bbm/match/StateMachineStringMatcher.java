package bbm.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
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
            for (int i = 0; i < pattern.length(); i++) {
                String sub = pattern.substring(0, i + 1);
                int rightIndex = characters.get(pattern.charAt(i));
                stateMap[i][rightIndex] = i + 1;
                for (Map.Entry<Character, Integer> entry: characters.entrySet()) {
                    if (!entry.getValue().equals(rightIndex)) {
                        for (int j = i; j >= 0; j++) {
                            String temp = sub + entry.getKey();
                            if (temp.endsWith(pattern.substring(0, j))) {
                                stateMap[i][entry.getValue()] = j;
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
                if (state == pattern.length() + 1) {
                    result.add(i - pattern.length());
                }
            }
        }
        return result;
    }
}
