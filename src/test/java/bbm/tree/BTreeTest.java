package bbm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
class BTreeTest {
    @Test
    public void testBTree() {
        int treeSize = 10;
        System.err.println("Building tree...");
        BTree tree = new BTree();
        Random rand = new Random(System.currentTimeMillis());
        List<Integer> insertSet = new ArrayList<>();
        int i;
        /*for (i = 0; i < treeSize; ++i) {
            int val = rand.nextInt(treeSize) + 1;

            try {
                tree.insert(val);
                insertSet.add(val);
                System.err.println("(Inserting value " + val + ")");
            } catch (Exception var11) {
                var11.printStackTrace();
                System.exit(1);
            }
        }*/
        tree.insert(7);
        insertSet.add(7);
        tree.insert(10);
        insertSet.add(10);
        tree.insert(6);
        insertSet.add(6);
        tree.insert(8);
        insertSet.add(8);
        tree.insert(4);
        insertSet.add(4);
        tree.insert(1);
        insertSet.add(1);
        tree.insert(5);
        insertSet.add(5);
        tree.insert(1);
        insertSet.add(1);
        tree.insert(6);
        insertSet.add(6);
        tree.insert(2);
        insertSet.add(2);

        System.err.println();
        System.err.println("Searching in tree...");

        for (int data : insertSet) {
            BTree.SearchResult result = tree.search(data);
            if (result == null || result.node.keys[result.index] != data) {
                throw new RuntimeException("Search error");
            }
        }

        for (i = 0; i < 100; i++) {
            int val = rand.nextInt(treeSize * 10) + 1;
            if (!insertSet.contains(val)) {
                if (tree.search(val) != null) {
                    throw new RuntimeException("Search error");
                }
            }
        }

        /*System.err.println();
        System.err.println("Churning tree...");

        Collections.shuffle(insertSet);
        Set<Integer> insertSet2 = new HashSet<>();
        insertSet.forEach(data -> {
            System.err.println("(Removing value " + data + ")");
            tree.delete(data);
            int newVal = rand.nextInt(treeSize) + 1;
            System.err.println("(Inserting value " + newVal + ")");
            tree.insert(newVal);
            insertSet2.add(newVal);
        });*/

        System.err.println();
        System.err.println("Clear tree...");
        insertSet.forEach(data -> {
            System.err.println("(Removing value " + data + ")");
            tree.delete(data);
        });
        if (tree.getRoot() != null && tree.getRoot().keySize != 0) {
            System.err.println("Not clean!!!!!!!!!!!");
        } else {
            System.err.println("All tests passed.");
        }
    }
}