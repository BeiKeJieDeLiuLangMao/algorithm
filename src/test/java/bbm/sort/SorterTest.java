package bbm.sort;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author bbm
 */
public class SorterTest {

    @Test
    public void testSort() {
        Sorter sorter = new BucketSorter();
        doTest(sorter);
        sorter = new CountingSorter();
        doTest(sorter);
        sorter = new HeapSorter();
        doTest(sorter);
        sorter = new InsertSorter();
        doTest(sorter);
        sorter = new MergeSorter();
        doTest(sorter);
        sorter = new QuickSorter();
        doTest(sorter);
        sorter = new RadixSorter();
        doTest(sorter);
    }

    private void doTest(Sorter sorter) {
        int dataSize = 10000;
        long time1 = 0;
        long time2 = 0;
        // 这里跑了很多次是为了减弱 JIT 的影响
        for (int x = 0; x < 100; x++) {
            int[] data = new int[dataSize];
            int[] data2 = new int[dataSize];
            Random rand = new Random(System.currentTimeMillis());
            for (int i = 0; i < dataSize; i++) {
                data[i] = rand.nextInt(dataSize);
                data2[i] = data[i];
            }
            long start = System.currentTimeMillis();
            Arrays.sort(data);
            time1 += System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            int[] result = sorter.sort(data2);
            time2 += System.currentTimeMillis() - start;
            for (int i = 0; i < dataSize; i++) {
                assertEquals(data[i], result[i]);
            }
        }
        System.out.println(sorter.getClass().getSimpleName() + ": jdk speeds: " + time1 + " ms, my algorithm speeds " + time2 + " ms");
    }
}
