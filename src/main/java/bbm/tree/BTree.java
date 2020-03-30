package bbm.tree;

/**
 * @author bbm
 */
public class BTree {
    private static final int t = 5;
    Node root;

    public BTree() {
        Node root = new Node();
        root.leaf = true;
        // write-disk root
        this.root = root;
    }

    /**
     * 搜索 B 树和搜索二叉树很像，但是它没法做到 2 路选择，而是一个多路选择
     *
     * 它将返回节点 y 和与 key 相等的下标 i，即 y.keys[i] = key, 如果 key 不存在的话，则返回 null
     */
    private SearchResult search(Node x, int key) {
        int i = 0;
        while (i < x.keySize && key > x.keys[i]) {
            i++;
        }
        if (i < x.keySize && key == x.keys[i]) {
            return new SearchResult(x, i);
        } else if (x.leaf) {
            return null;
        } else {
            // read-disk x.children[i]
            return search(x.children[i], key);
        }
    }

    public SearchResult search(int key) {
        return search(root, key);
    }

    /**
     * 使用此函数来拆分 x.children[i]，它为一个满节点，主要分两步
     * 1. 新建一个节点 z，将 x.children[i] 的后半段拷贝进 z，包括 keys 和 children
     * 2. 在 x 节点的 keys 中插入 x.children[i] 和 z 的中间 key，并将 z 插入到 x 的 children 中
     */
    private void splitChild(Node x, int i) {
        Node z = new Node();
        Node y = x.children[i];
        z.leaf = y.leaf;
        z.keySize = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[t + j];
            y.keys[t + j] = 0;
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[t + j];
                y.children[t + j] = null;
            }
        }
        y.keySize = t - 1;
        for (int j = x.keySize; j >= i + 1; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;
        for (int j = x.keySize - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        y.keys[t-1] = 0;
        x.keySize = x.keySize + 1;
        // write-disk x
        // write-disk y
        // write-disk z
    }

    /**
     * 插入过程要保证在查找合适位置的过程中将所有已满的节点进行拆分，从 root 节点出发，沿着树向下遍历，必要时通过 split child 进行拆分
     */
    public void insert(int k) {
        Node r = root;
        if (root.keySize == 2 * t - 1) {
            // 该步保证，插入过程中 root 节点肯定不是满节点
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.keySize = 0;
            s.children[0] = r;
            // 该步实际上会将 r.keys[t-1] 拷贝到 s 的 keys 中，并将 r 的 children 拆分
            splitChild(s, 0);
            insertNonFull(s, k);
        } else {
            insertNonFull(r, k);
        }
        verify();
    }

    /**
     * 将关键字 k 插入到 x 节点中，假设 x 不是满的，{@link BTree#insert(int)} 和 {@link BTree#splitChild(bbm.tree.BTree.Node, int)}
     * 将保证该假设成立
     */
    private void insertNonFull(Node x, int k) {
        int i = x.keySize - 1;
        if (x.leaf) {
            // 只有叶子结点才能往 keys 插入新值
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.keySize += 1;
            // disk-write x
        } else {
            // 不是叶子结点，看看 k 应该属于哪个 child node
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i = i + 1;
            // disk-read x.children[i]
            if (x.children[i].keySize == 2 * t - 1) {
                // 这一步保证当 k 要插入到一个子节点时，该节点绝对不是一个满节点
                splitChild(x, i);
                // 拆分之后，要查看一下 key 应该插入到拆分之后的哪个新 node 中
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    /**
     * 考虑到根结点的特殊性,对根结点为1,并且两个子结点都是t-1的情况进行了特殊的处理：
     * 先对两个子结点进行合并,然后把原来的根删除,把树根指向合并后的子结点y。
     * 这样B树的高度就减少了1。这也是B树高度唯一会减少的情况。
     */
    public void delete(int k) {
        Node r = root;
        if (r.keySize == 1 && !r.leaf) {
            // disk read r.children[0]
            // disk read r.children[1]
            Node y = r.children[0];
            Node z = r.children[1];
            if (y.keySize == z.keySize && y.keySize == t - 1) {
                mergeChild(r, 0, y, z);
                root = y;
                deleteNonOne(y, k);
            } else {
                deleteNonOne(r, k);
            }
        } else {
            deleteNonOne(r, k);
        }
        verify();
    }

    private void mergeChild(Node r, int i, Node y, Node z) {
        y.keySize = 2 * t - 1;
        for (int j = t; j < 2 * t - 1; j++) {
            y.keys[j] = z.keys[j - t];
        }
        y.keys[t - 1] = r.keys[i];
        if (!y.leaf) {
            for (int j = t; j < 2 * t; j++) {
                y.children[j] = z.children[j - t];
            }
        }
        for (int j = i + 1; j < r.keySize; j++) {
            r.children[j] = r.children[j + 1];
        }
        for (int j = i; j < r.keySize - 1; j++) {
            r.keys[j] = r.keys[j + 1];
        }

        r.keySize -= 1;
        r.keys[r.keySize] = 0;
        r.children[r.keySize + 1] = null;
        // disk-write y
        // disk-write z
        // disk-write x
    }

    private void deleteNonOne(Node x, int k) {
        int i = 0;
        if (x.leaf) {
            // 当 x 是叶子结点，找到 k 所在的位置，将其删除，并将 k 之后的 keys 左移
            while (i < x.keySize && k > x.keys[i]) {
                i++;
            }
            if (i < x.keySize && k == x.keys[i]) {
                for (int j = i + 1; j < x.keySize; j++) {
                    x.keys[j - 1] = x.keys[j];
                }
                x.keySize -= 1;
                x.keys[x.keySize] = 0;
                // disk-write x
            } else {
                System.out.println(k + ": key not exist in tree");
            }
        } else {
            while (i < x.keySize && k > x.keys[i]) {
                i++;
            }
            // disk-read x.children[i]
            Node y = x.children[i];
            Node z = null;
            if (i < x.keySize) {
                // disk-read x.children[i]
                z = x.children[i + 1];
            }
            if (i < x.keySize && k == x.keys[i]) {
                // 如果找到的 k 不处于叶子结点中，我们需要为其找到替换者以保持每个非 root 节点的 key size > t-1
                if (y.keySize > t - 1) {
                    // 如果小于 k 的子 node key size > t -1 用其最大的 key 替换待删除的 k
                    int newK = searchPredecessor(y);
                    deleteNonOne(y, newK);
                    x.keys[i] = newK;
                } else if (z.keySize > t - 1) {
                    // 如果大于 k 的子 node key size > t -1 用其最小的 key 替换待删除的 k
                    int newK = searchSuccessor(z);
                    deleteNonOne(z, newK);
                    x.keys[i] = newK;
                } else {
                    // 如果两边的子 node key size 都不够大，就把它们两个合并，然后在删除 k
                    mergeChild(x, i, y, z);
                    deleteNonOne(y, k);
                }
            } else {
                Node p = null;
                if (i > 0) {
                    // disk-read x.children[i - 1]
                    p = x.children[i - 1];
                }
                if (y.keySize == t - 1) {
                    // 如果 k 节点所处的 node 节点太少的话，我们得从两边借一个节点挪进去
                    if (i > 0 && p.keySize > t - 1) {
                        // 左边的 node children 多，就借一个
                        shift2RightChild(x, i-1, p, y);
                    } else if (i < x.keySize && z.keySize > t - 1) {
                        // 右边的 node children 多，就借一个
                        shift2LeftChild(x, i, y, z);
                    } else if (i > 0) {
                        // 左右节点的子节点都不够，如果有左节点，则左节点和 y 合并
                        mergeChild(x, i-1, p, y);
                        y = p;
                    } else {
                        // 左右节点的子节点都不够，如果有右节点，则右节点和 y 合并
                        mergeChild(x, i, y, z);
                    }
                }
                deleteNonOne(y, k);
            }
        }
    }

    private void shift2LeftChild(Node x, int i, Node y, Node z) {
        y.keys[y.keySize] = x.keys[i];
        y.keySize += 1;
        x.keys[i] = z.keys[0];
        z.keySize -= 1;
        for (int j = 0; j < z.keySize; j++) {
            z.keys[j] = z.keys[j + 1];
        }
        z.keys[z.keySize] = 0;
        if (!z.leaf) {
            y.children[y.keySize] = z.children[0];
            for (int j = 0; j <= z.keySize; j++) {
                z.children[j] = z.children[j + 1];
            }
            z.children[z.keySize + 1] = null;
        }
        // disk-write x
        // disk-write y
        // disk-write z
    }

    private void shift2RightChild(Node x, int i, Node y, Node z) {
        z.keySize += 1;
        for (int j = z.keySize - 1; j > 0; j--) {
            z.keys[j] = z.keys[j - 1];
        }
        z.keys[0] = x.keys[i];
        x.keys[i] = y.keys[y.keySize - 1];
        if (!z.leaf) {
            for (int j = z.keySize - 1; j >= 0; j--) {
                z.children[j + 1] = z.children[j];
            }
            z.children[0] = y.children[y.keySize];
            y.children[y.keySize] = null;
        }
        y.keySize -= 1;
        y.keys[y.keySize] = 0;
        // disk-write x
        // disk-write y
        // disk-write z
    }

    private void verify() {
        if (root == null) {
            return;
        }
        verifyFromNode(root);
    }

    private void verifyFromNode(Node x) {
        if (x != root && x.keySize < (t - 1)) {
            throw new RuntimeException("节点关键字小于 t-1");
        }
        if (x.keySize > (2 * t - 1)) {
            throw new RuntimeException("节点关键字大于 2 * t - 1");
        }
        for (int i = 1; i < x.keySize; i++) {
            if (x.keys[i - 1] > x.keys[i]) {
                throw new RuntimeException("节点 keys 不是升序");
            }
        }
        if (!x.leaf) {
            for (int i = 0; i < x.keySize; i++) {
                if (x.children[i].keys[x.children[i].keySize - 1] > x.keys[i]) {
                    throw new RuntimeException("左子节点的 max key 比当前 key 大");
                }
                if (x.children[i + 1].keys[0] < x.keys[i]) {
                    throw new RuntimeException("右子节点的 min key 比当前 key 小");
                }
            }
        }
    }

    private int searchPredecessor(Node z) {
        Node x = z;
        int i = x.keySize;
        while (!x.leaf) {
            // disk-read x.children[i]
            x = x.children[i];
            i = x.keySize;
        }
        return x.keys[x.keySize - 1];
    }

    private int searchSuccessor(Node z) {
        Node x = z;
        while (!x.leaf) {
            // disk-read x.children[0]
            x = x.children[0];
        }
        return x.keys[0];
    }

    public Node getRoot() {
        return root;
    }

    public static class Node {
        int[] keys = new int[2 * t - 1];
        int keySize;
        Node[] children = new Node[2 * t];
        boolean leaf;
    }

    public static class SearchResult {
        Node node;
        int index;

        public SearchResult(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
