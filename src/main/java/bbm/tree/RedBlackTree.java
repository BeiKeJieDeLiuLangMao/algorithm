package bbm.tree;

import static bbm.tree.RedBlackTree.Color.BLACK;
import static bbm.tree.RedBlackTree.Color.RED;

/**
 * 1.每个结点或是红色的, 或是黑色的。
 * 2.根结点是黑色的。
 * 3.每个叶结点(NIL)是黑色的。
 * 4.如果一个结点是红色的, 则它的两个子结点都是黑色的。
 * 5.对每个结点, 从该结点到其所有后代叶结点的简单路径上, 均包含相同数目的黑色结点。
 *
 * 根据上述性质任何节点的简单路径上，高度差不会超过 2 倍，即一个包含 n 个节点的红黑树高度最多是 2log(n + 1)
 *
 * @author bbm
 */
public class RedBlackTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 旋转的意义是为了保持性质 5（黑节点高度相同），并且要保证旋转之后仍然满足二叉查找树的性质（左节点 < 父节点 < 右节点）
     *
     *      10(x)                   12(y)
     *      /\                      /\
     *     9  12(y)     ->      10(x) 13
     *        /\                /\
     *       11 13             9  11
     *
     * y 接替 x 的位置，y 的左节点作为 x 右节点
     */
    private void leftRotate(Node x) {
        Node y = x.getRight();
        // 挪动 y 的左节点
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        // y 替换 x 的位置
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        // x 成为 y 的左节点
        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * 旋转的意义是为了保持性质 5（黑节点高度相同），并且要保证旋转之后仍然满足二叉查找树的性质（左节点 < 父节点 < 右节点）
     *
     *      10(x)               8(y)
     *      /\                  /\
     *    8(y) 11   ->         6 10(x)
     *    /\                      /\
     *   6 9                     9  11
     *
     * y 接替 x 的位置，y 的右节点作为 x 左节点
     */
    private void rightRotate(Node x) {
        Node y = x.getLeft();
        // 挪动 y 的右节点, 到 x 左节点
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        // y 替换 x 的位置
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        // x 成为 y 的右节点
        y.setRight(x);
        x.setParent(y);
    }

    /**
     *
     */
    public void insert(Node z) {
        Node y = null;
        Node x = this.root;
        // 从 root 出发，找到新节点 z 的合适位置, y 记录着合适位置的父节点
        while(x != null) {
            y = x;
            if (z.getData() < x.getData()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        // 更新新节点 z 的父节点和 y 的子节点
        z.setParent(y);
        if (y == null) {
            this.root = z;
        } else if (z.getData() < y.getData()) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }
        // 每次插入的新节点它的颜色最初必须是红色，否则就违反了性质5（黑高相同）
        z.setColor(RED);
        insertFixUp(z);
    }

    /**
     * 修正节点颜色，没当我们把一个节点的颜色置为红色是，就得看看是不是需要修正一下颜色
     *
     * 我们面临如下情况：
     * 1. 新节点是根，只需要将其颜色置为黑
     * 2. 新节点非根：
     *    2.1 父节点是黑，无需调整，不影响任何性质
     *    2.2 父节点是红，这时候如果只是简单的修改父节点，使其变为黑色，那么黑高可能就不等了，看来我们需要保证父节点和叔节点的黑高相同
     *    换句话说，没当我们要将一个节点变色时，我们要保证该节点的黑高与兄弟节点相等，即成对的变色
     *    这时候我们分析一下，如果父节点是红色，那么祖父节点肯定不是红色，否则就违反了性质4（不存在红父子），这时候我们来看一下叔节点的颜色可能
     *          2.2.1 叔节点是红色, 它和父节点颜色相同，为了保持它们的高度差，简单的思路是将它们一起变为黑色，
     *          但这时候我们不能简单的将父节点和叔节点置为黑色，因为这样会导致它们的黑高都 +1，祖父节点的黑高也会变化，为了维持黑高
     *          我们要修改祖父节点的颜色，将他变为红色（祖父节点之前肯定是黑色），这样父节点和叔节点的黑高就 + 1 之后 - 1，高度得到保持
     *               B(grandParent)                  R(grandParent)
     *                  /\                                /\
     *          R(parent) R(uncle)               B(parent) B(uncle)
     *               /                              /
     *           R(z)                             R(z)
     *          这时候因为我们又把祖父节点置为红色了，所以我们得递归的保证祖父节点能满足性质 4（不存在红父子）和性质2（根节点是黑色）
     *          2.2.2 叔节点是黑色，我们没法简单地通过变色来保持黑高了，这时候就得旋转了，接下来我们又得分两种情况
     *              2.2.2.1 z 是父节点的左节点，这时候我们先交换父节点和祖父节点的颜色，会导致叔节点的黑高 -1，然后我们再右旋一下
     *              让父节点接替祖父节点的位置，这时候两边的黑高就平衡了, 而且也不存在相邻的红节点
     *                B(grandParent)                  R(grandParent)                   B(parent)
     *                      /\                               /\                            /\
     *              R(parent) B(uncle)               B(parent) B(uncle)                 R(z)  R(grandParent)
     *                   /                              /                                      \
     *                 R(z)                          R(z)                                       B(uncle)
     *              2.2.2.2 z 是父节点的右节点时，按照 2.2.2.1 右旋是会有问题的，这时候虽然黑高平衡但是又会出现新的红节点相邻
     *               B(grandParent)                  R(grandParent)                   B(parent)
     *                      /\                              /\                            \
     *              R(parent) B(uncle)               B(parent) B(uncle)                   R(grandParent)
     *                    \                              \                                     / \
     *                    R(z)                           R(z)                               R(z)   B(uncle)
     *              所以这时候我们需要先进行一次左旋，使 2.2.2.2 的形态变为 2.2.2.1，然后再按照 2.2.2.1 的方式处理
     *                B(grandParent)                  R(grandParent)
     *                      /\                               /\
     *             R(parent) B(uncle)                   R(z)   B(uncle)
     *                   \                              /
     *                  R(z)                          R(parent)
     *
     */
    private void insertFixUp(Node z) {
        while(z != this.root && z.getParent().getColor() == RED) {
            Node y;
            Node grandParent = z.getParent().getParent();
            if (z.getParent() == grandParent.getLeft()) {
                Node uncle = grandParent.getRight();
                if (uncle != null && uncle.getColor() == RED) {
                    // 叔节点是红，按 2.2.1 情况处理
                    z.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    grandParent.setColor(RED);
                    // 再检查一下祖父节点颜色是否满足性质
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        // 情况 2.2.2.2 先通过旋转转化为 2.2.2.1
                        z = z.getParent();
                        this.leftRotate(z);
                    }
                    // 2.2.2.1 ，交换父节点和曾祖父颜色，然后旋转
                    z.getParent().setColor(BLACK);
                    grandParent.setColor(RED);
                    this.rightRotate(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if (y != null && y.getColor() == RED) {
                    // 叔节点是红，按 2.2.1 情况处理
                    z.getParent().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    // 再检查一下祖父节点颜色是否满足性质
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        // 情况 2.2.2.2 先通过旋转转化为 2.2.2.1
                        z = z.getParent();
                        this.rightRotate(z);
                    }
                    // 2.2.2.1 ，交换父节点和曾祖父颜色，然后旋转
                    z.getParent().setColor(BLACK);
                    z.getParent().getParent().setColor(RED);
                    this.leftRotate(z.getParent().getParent());
                }
            }
        }
        // 待修正的节点是根，或者其父节点是黑，那么我们只需要保证 root 是黑就够了
        this.root.setColor(BLACK);
        this.verify();
    }

    /**
     * 验证当前树是否满足所有性质
     */
    private void verify() {
        if (root != null && root.color == RED) {
            throw new RuntimeException("违反性质2: 根结点是黑色的");
        }
        verifyFromNode(root);
    }

    private int verifyFromNode(Node node) {
        if (node == null) {
            // 叶子结点是 null 所以这个不检查其颜色，null 即代表黑色的叶子结点
            return 1;
        } else if (node.getColor() != RED && node.getColor() != BLACK) {
            throw new RuntimeException("违反性质1: 每个结点或是红色的, 或是黑色的");
        } else {
            if (node.getColor() == RED) {
                if (node.getLeft() != null && node.getLeft().getColor() != BLACK) {
                    throw new RuntimeException("违反性质4: 如果一个结点是红色的, 则它的两个子结点都是黑色的");
                }

                if (node.getRight() != null && node.getRight().getColor() != BLACK) {
                    throw new RuntimeException("违反性质4: 如果一个结点是红色的, 则它的两个子结点都是黑色的");
                }
            }
            int i = this.verifyFromNode(node.getLeft());
            int j = this.verifyFromNode(node.getRight());
            if (i != j) {
                throw new RuntimeException("违反性质5: 对每个结点, 从该结点到其所有后代叶结点的简单路径上, 均包含相同数目的黑色结点 (left black count = " + i + ", right black count = " + j + ")");
            } else {
                return i + (node.getColor() == RED ? 0 : 1);
            }
        }
    }

    /**
     * 删除节点有三种情况：
     * 1. 节点 z 没有子节点，这时候只需要修改起父节点，用 null 代替 z
     * 2. 节点 z 只有一个子节点，那么用该子节点代替 z
     * 3. 节点 z 有2个子节点，这时候我们需要找到右子树中最小的节点，让这个最小的节点代替 z
     */
    public void delete(Node z) {
        Node colorNode = z;
        Node movedNode;
        Node movedNodeFinalParent;
        if (z.getLeft() != null && z.getRight() != null) {
            // 情况3 节点 z 有2个子节点，这时候我们需要找到右子树中最小的节点，让这个最小的节点代替 z
            Node successor = treeMinimum(z);
            colorNode = successor;
            Node successorRight = successor.getRight();
            movedNode = successorRight;
            movedNodeFinalParent = successor.getParent();
            if (successorRight != null) {
                successorRight.setParent(successor.getParent());
            }
            if (successor.getParent().getLeft() == successor) {
                successor.getParent().setLeft(successorRight);
            } else {
                successor.getParent().setRight(successorRight);
            }
            z.copyFrom(successor);
        } else if (z.getLeft() != null) {
            // 情况2 节点 z 有1个子节点，那么用该子节点代替 z
            movedNode = z.getLeft();
            movedNodeFinalParent = z.getParent();
            fixParentReference(z, z.getLeft());
        } else if (z.getRight() != null) {
            // 情况2 节点 z 有1个子节点，那么用该子节点代替 z
            movedNode = z.getRight();
            movedNodeFinalParent = z.getParent();
            fixParentReference(z, z.getRight());
        } else {
            // 情况1 节点 z 没有子节点，这时候只需要修改起父节点，用 null 代替 z
            movedNode = null;
            movedNodeFinalParent = z.getParent();
            fixParentReference(z, null);
        }
        if (colorNode.getColor() == BLACK) {
            this.deleteFixup(movedNode, movedNodeFinalParent);
        }
        this.verify();
    }

    private void fixParentReference(Node deleted, Node newNode) {
        if (deleted.getParent() == null) {
            root = newNode;
        } else if (deleted == deleted.getParent().getLeft()) {
            deleted.getParent().setLeft(newNode);
        } else {
            deleted.getParent().setRight(newNode);
        }
        if (newNode != null) {
            newNode.setParent(deleted.getParent());
        }
    }

    private Node treeMinimum(Node x) {
        while(x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    private void deleteFixup(Node x, Node xParent) {
        while(x != this.root && (x == null || x.getColor() == BLACK)) {
            Node w;
            if (x == xParent.getLeft()) {
                w = xParent.getRight();
                if (w == null) {
                    throw new RuntimeException("x's sibling should not be null");
                }

                if (w.getColor() == RED) {
                    w.setColor(BLACK);
                    xParent.setColor(RED);
                    this.leftRotate(xParent);
                    w = xParent.getRight();
                }

                if ((w.getLeft() == null || w.getLeft().getColor() == BLACK) && (w.getRight() == null || w.getRight().getColor() == BLACK)) {
                    w.setColor(RED);
                    x = xParent;
                    xParent = xParent.getParent();
                } else {
                    if (w.getRight() == null || w.getRight().getColor() == BLACK) {
                        w.getLeft().setColor(BLACK);
                        w.setColor(RED);
                        this.rightRotate(w);
                        w = xParent.getRight();
                    }

                    w.setColor(xParent.getColor());
                    xParent.setColor(BLACK);
                    if (w.getRight() != null) {
                        w.getRight().setColor(BLACK);
                    }

                    this.leftRotate(xParent);
                    x = this.root;
                    xParent = x.getParent();
                }
            } else {
                w = xParent.getLeft();
                if (w == null) {
                    throw new RuntimeException("x's sibling should not be null");
                }

                if (w.getColor() == RED) {
                    w.setColor(BLACK);
                    xParent.setColor(RED);
                    this.rightRotate(xParent);
                    w = xParent.getLeft();
                }

                if ((w.getRight() == null || w.getRight().getColor() == BLACK) && (w.getLeft() == null || w.getLeft().getColor() == BLACK)) {
                    w.setColor(RED);
                    x = xParent;
                    xParent = xParent.getParent();
                } else {
                    if (w.getLeft() == null || w.getLeft().getColor() == BLACK) {
                        w.getRight().setColor(BLACK);
                        w.setColor(RED);
                        this.leftRotate(w);
                        w = xParent.getLeft();
                    }

                    w.setColor(xParent.getColor());
                    xParent.setColor(BLACK);
                    if (w.getLeft() != null) {
                        w.getLeft().setColor(BLACK);
                    }

                    this.rightRotate(xParent);
                    x = this.root;
                    xParent = x.getParent();
                }
            }
        }

        if (x != null) {
            x.setColor(BLACK);
        }

    }

    public static class Color {
        public static final Color RED = new Color("red");
        public static final Color BLACK = new Color("black");
        private String name;

        public String getName() {
            return this.name;
        }

        private Color(String name) {
            this.name = name;
        }
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;
        private Color color;

        public Node(int data) {
            this.data = data;
            this.color = RED;
        }

        public int getData() {
            return this.data;
        }

        public void copyFrom(Node arg) {
            this.data = arg.data;
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Node getParent() {
            return this.parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
