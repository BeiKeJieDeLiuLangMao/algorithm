package bbm.tree;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
class BTreeTest {
    @Test
    public void testBTree() {
        int treeSize = 10000;
        System.err.println("Building tree...");
        BTree tree = new BTree();
        Random rand = new Random(System.currentTimeMillis());
        List<Integer> insertSet = new ArrayList<>();
        int i;
        for (i = 0; i < treeSize; ++i) {
            int val = rand.nextInt(treeSize) + 1;

            try {
                tree.insert(val);
                insertSet.add(val);
                System.err.println("(Inserting value " + val + ")");
            } catch (Exception var11) {
                var11.printStackTrace();
                System.exit(1);
            }
        }
        System.out.println(insertSet.toString());
        //insertSet.forEach(tree::insert);

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

        System.err.println();
        System.err.println("Churning tree...");

        Collections.shuffle(insertSet);
        List<Integer> insertSet2 = new ArrayList<>();
        insertSet.forEach(data -> {
            System.err.println("(Removing value " + data + ")");
            tree.delete(data);
            int newVal = rand.nextInt(treeSize) + 1;
            System.err.println("(Inserting value " + newVal + ")");
            tree.insert(newVal);
            insertSet2.add(newVal);
        });

        System.err.println();
        System.err.println("Clear tree...");
        insertSet2.forEach(data -> {
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