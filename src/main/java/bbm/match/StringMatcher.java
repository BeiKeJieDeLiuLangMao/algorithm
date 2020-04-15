package bbm.match;

import java.util.List;

/**
 * @author bbm
 */
public interface StringMatcher {
    List<Integer> match(String string, String pattern);
}
