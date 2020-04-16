package bbm.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
class StringMatcherTest {

    @Test
    public void testNative() {
        List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'x');
        for (int loop = 0; loop < 10000; loop++) {
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < 100; i++) {
                int index = random.nextInt(chars.size());
                stringBuilder.append(chars.get(index));
            }
            String string = stringBuilder.toString();
            int start = random.nextInt(100);
            int end = start + random.nextInt(100 - start);
            String patternString = string.substring(start, end);
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(string);
            List<Integer> result = new ArrayList<>();
            while (matcher.find()) {
                result.add(matcher.start());
            }
            StringMatcher stringMatcher = new KMP();
            List<Integer> myResult = stringMatcher.match(string, patternString);
            try {
                Assertions.assertEquals(result.size(), myResult.size());
                for (int i = 0; i < result.size(); i++) {
                    Assertions.assertEquals(result.get(i), myResult.get(i));
                }
            } catch (Throwable e) {
                System.out.println("Pattern:" + patternString);
                System.out.println("String:" + string);
                throw e;
            }
        }
    }

    @Test
    public void test() {
        KMP kmp = new KMP();
        kmp.match("bbc abcdab abcdabcdabde", "abcdabd");
    }
}