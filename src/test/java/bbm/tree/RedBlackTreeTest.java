package bbm.tree;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * @author bbm
 */
public class RedBlackTreeTest {
    @Test
    public void testRedBlackTree() {
        int treeSize = 1000;
        System.err.println("Building tree...");
        RedBlackTree tree = new RedBlackTree();
        Random rand = new Random(System.currentTimeMillis());

        int i;
        for (i = 0; i < treeSize; ++i) {
            int val = rand.nextInt(treeSize) + 1;

            try {
                tree.insert(new RedBlackTree.Node(val));
                if (i > 0 && i % 100 == 0) {
                    System.err.print(i + "...");
                    System.err.flush();
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                System.exit(1);
            }
        }

        System.err.println();
        System.err.println("Churning tree...");

        for (i = 0; i < treeSize; ++i) {
            System.err.println("Iteration " + i + ":");
            RedBlackTree.Node xParent = null;
            RedBlackTree.Node x = tree.getRoot();

            int depth;
            for (depth = 0; x != null; ++depth) {
                xParent = x;
                if (rand.nextBoolean()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }

            int height = rand.nextInt(depth);
            if (height >= depth) {
                throw new RuntimeException("bug in java.util.Random");
            }

            while (height > 0) {
                xParent = xParent.getParent();
                --height;
            }

            System.err.println("(Removing value " + xParent.getData() + ")");
            tree.delete(xParent);
            int newVal = rand.nextInt(treeSize) + 1;
            System.err.println("(Inserting value " + newVal + ")");
            tree.insert(new RedBlackTree.Node(newVal));
        }

        System.err.println();
        System.err.println("Clear tree...");
        for (i = 0; i < treeSize; ++i) {
            System.err.println("Iteration " + i + ":");
            RedBlackTree.Node xParent = null;
            RedBlackTree.Node x = tree.getRoot();

            int depth;
            for (depth = 0; x != null; ++depth) {
                xParent = x;
                if (rand.nextBoolean()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }

            int height = rand.nextInt(depth);
            if (height >= depth) {
                throw new RuntimeException("bug in java.util.Random");
            }

            while (height > 0) {
                xParent = xParent.getParent();
                --height;
            }

            System.err.println("(Removing value " + xParent.getData() + ")");
            tree.delete(xParent);
        }
        if (tree.getRoot() != null) {
            System.err.println("Not clean!!!!!!!!!!!");
        } else {
            System.err.println("All tests passed.");
        }
    }
}
