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
     * 1. 找到正确的位置
     * 2. 修正引用关系
     * 3. 新节点置为红色
     * 4. 修正颜色性质
     */
    public void insert(Node z) {
        Node y = null;
        Node x = this.root;
        // 从 root 出发，找到新节点 z 的合适位置, y 记录着合适位置的父节点
        while (x != null) {
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
     * 修正节点颜色，没当我们把一个节点的颜色置为红色是，就得看看是不是需要修正一下颜色, 本文中 B = Black R = Red
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
        while (z != this.root && z.getParent().getColor() == RED) {
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
            if (node.getLeft() != null) {
                if (node.getLeft().getData() > node.getData()) {
                    throw new RuntimeException("违反二叉树性质: 左节点应小于父节点");
                }
            }
            if (node.getRight() != null) {
                if (node.getRight().getData() < node.getData()) {
                    throw new RuntimeException("违反二叉树性质: 右节点应大于父节点");
                }
            }
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
     * 3. 节点 z 有2个子节点，这时候我们需要找到右子树中最小的节点，让这个最小的节点和 z 互换，然后删除右子树的最小节点
     * 这就将带删除节点是有两个子节点的问题转化成了待删除节点最多只有一个右节点的问题
     *
     * 删掉节点后，我们需要检查红黑树的性质是否仍然满足，这里就需要检查真正移除节点的颜色了，如果移除节点是红色，那么移除它黑高不会受到影响
     * 而且红节点的父节点必是黑色，如果有子节点肯定也是黑色，删除中间的红节点后，变成黑色的父节点和黑色的子节点相连，也不违反性质
     *      B(parent)      B(parent)
     *         /              /
     *      R(z)           B(child)
     *      /
     *    B(child)
     * 本文中 B = Black R = Red
     *
     * 看来只会在被删除节点是黑色时才会出现问题，因为删掉它必然导致所有子节点黑高 - 1，而且也会出现红红相邻的问题
     * 在 {@link RedBlackTree#deleteFixup(Node, Node)} 我们挨个分析这类情况
     */
    public void delete(Node z) {
        Node removedNode = z;
        Node removedNodeParent = z.getParent();
        Node successor;
        if (z.getLeft() != null && z.getRight() != null) {
            // 情况3 节点 z 有2个子节点，这时候我们需要找到右子树中最小的节点，让这个最小的节点和 z 互换身份，然后删除右子树的最小节点
            Node rightSubTreeMinNode = treeMinimum(z.getRight());
            // 右子树最小节点和待删除节点 z 互换，这里只换值，相当于 z 已经删除了
            z.copyFrom(rightSubTreeMinNode);
            // 互换完成后，我们的问题就转化为删除右子树最小节点了
            removedNode = rightSubTreeMinNode;
            removedNodeParent = rightSubTreeMinNode.getParent();
            successor = rightSubTreeMinNode.getRight();
            fixReference(rightSubTreeMinNode, rightSubTreeMinNode.getRight());
        } else if (z.getLeft() != null) {
            // 情况2 节点 z 有1个子节点，那么用该子节点代替 z
            successor = z.getLeft();
            fixReference(z, z.getLeft());
        } else if (z.getRight() != null) {
            // 情况2 节点 z 有1个子节点，那么用该子节点代替 z
            successor = z.getRight();
            fixReference(z, z.getRight());
        } else {
            // 情况1 节点 z 没有子节点，这时候只需要修改起父节点，用 null 代替 z
            successor = null;
            fixReference(z, null);
        }
        if (removedNode.getColor() == BLACK) {
            this.deleteFixup(successor, removedNodeParent);
        }
        this.verify();
    }

    private void fixReference(Node deleted, Node newNode) {
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
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    /**
     * 在上一步中，我们已经将待删除节点有两个子节点的问题转化为待删除节点最多只有一个子节点 x 的问题，接下来我们以子节点 x 是左节点为例
     * 分情况讨论，本文中 B = Black R = Red：
     * 1. 如果该节点的孩子 x 为红色，直接拿孩子节点 x 替换被删除的节点，并将孩子节点染成黑色，即可恢复性质5（黑高相同）。
     *           ?(xParent)             ?(xParent)
     *           /   \                    /  \
     *    B（deleted）?(brother)  ->    B(x) ?(brother)
     *       /
     *     R(x)
     * 2. 又或者删除的节点是根节点，这时候整个树的黑高都会 -1，最终任意路径黑高都是相等的，删除之后接替者 x 变成了根，这时候我们只需要保证
     * 性质2（根节点是黑色）即可
     *      B(deleted root)                B(x root)
     *        /                ->
     *     ？(x)
     * 3. 接下来我们看一下复杂的情况，即被删除的子节点 x 的颜色是黑色的情况, 在开始之前我们先要只要待删除节点是肯定有兄弟节点的
     * 因为待删除节点是黑色，如果它没有兄弟节点的话，parent 的左右两条链路黑高就不等了所以在情况 1 中我的图示中才画了了一个颜色未知的 brother 节点
     *      ?(xParent)
     *       /   \
     *   B(x)   ?(brother)
     * 在接下来的讨论中，我们不再考虑被删除的节点，直接讨论删除之后的节点关系图，如上图所示。
     *      3.1 这里我们先看 brother 是黑色的情况：
     *          3.1.1 x 的兄弟节点 brother 是黑色，且 brother 的两个子节点都是黑色, 因为之前的删除操作导致 x 黑高 -1，这时候为了弥补左支的黑高，
     *          我们将右支的黑高也 -1，即 brother 变为红色，这时候左右两支的黑高就平衡了，但是通过 parent 的黑高都 -1 了，这时候我们递归的处理
     *          parent 节点黑高 -1 的问题，即 parent -> x, parent.parent -> xParent, 如果这样递归向上处理势必最红会到达根节点，
     *          届时整个树的黑高都会 -1，又或者通过如下的其他方式达到平衡
     *              ?(xParent)                  ?(xParent)                           ?(newX)
     *              /   \                          /   \                             /   \
     *          B(x)   B(brother)              B(x)    R(brother)           B(previousX) R(previousBrother)
     *                  /     \                         /     \                            /     \
     *              B(BLeft)  B(BRight)               B(BLeft)  B(BRight)             B(PBLeft)  B(PBRight)
     *
     *          3.1.2 x 的兄弟节点 brother 是黑色，并且 brother 的右孩子是红色（brother 的左孩子可以是任意），这时候我们将 brother
     *          节点变为 parent 的颜色，然后将 parent 节点变为黑色，brother 的右子节点变为黑色，然后我们以 parent 为中心左旋，左旋之后
     *          你会发现之前的 x 节点黑高 +1 了，并且之前兄弟节点的左子节点的链路仍然保持 ? -> B -> ?, 右子节点的链路由 ? -> B -> R 变为
     *          ？ -> B 但是黑高仍然没变，也就是说通过上述操作让 x 黑高保持不变，并且 x 的 brother 的子节点黑高不变
     *              ?(xParent)                  B(xParent)                           ?(previousBrother)
     *              /   \                          /   \                             /           \
     *          B(x)   B(brother)              B(x)    ?(brother)           B(previousParent) B(PBRight)
     *                  /     \                         /     \                  /      \
     *              ？(BLeft)  R(BRight)               ?(BLeft)  B(BRight) B(previousX) ?(PBLeft)
     *
     *          3.1.3 x 的兄弟节点 brother 是黑色，并且 brother 的左节点红色，右节点是黑色，这时候我们将 brother 节点变为红色，brother 的
     *          左节点变为黑色，然后以 brother 为中心右旋，这两部操作能够保证 brother 的左右子节点黑高不变，brother 的左节点从 ? -> B -> R
     *          变成了 ? -> B, brother 的右节点从 ? -> B -> B 变成 ? -> B -> R -> B，它们的黑高都没变，这两部操作看似好像没啥实际作用，
     *          但它成功地将情况 3.1.3 转化为了情况 3.1.2，之后只需要按照情况 3.1.2 处理即可
     *              ?(xParent)                  ?(xParent)                           ?(x)
     *              /   \                          /   \                             /   \
     *          B(x)   B(brother)              B(x)    R(brother)           B(previousX) B(PBLeft)
     *                 /     \                         /     \                               \
     *              R(BLeft)  B(BRight)           B(BLeft)  B(BRight)                 R(previousBrother)
     *                                                                                         \
     *                                                                                       B(PBRight)
     *
     *       3.2 然后我们再看看 brother 是红色的情况，只要我们将 brother 变为黑色，parent 变为红色，然后以 parent 为中心左旋，就能让
     *       brother 的左右节点黑高不变，brother 的左节点由 B -> R -> B 变为 B -> R -> B, 右节点由 B -> R -> B 变为 B -> B
     *       但是之前的 X 节点黑高也没有发生变化，即由 B -> B 变为 B -> R -> B，但是这时候观察之前的 x 节点和 brother 节点的左节点
     *       的位置，你会发现实际上之前 brother 的左节点已经变成了 x 的新 brother，并且该 brother 是黑色，这就将情况 3.2 转化为了
     *       情况 3.1，接下来我们可以按照情况 3.1 的方式来处理了
     *           B(xParent)                      R(xParent)                           B(previousBrother)
     *               /   \                          /   \                             /   \
     *           B(x)   R(brother)              B(x)    B(brother)           R(previousParent) B(PBRight)
     *                  /     \                         /     \                      /   \
     *              B(BLeft)  B(BRight)               B(BLeft)  B(BRight)   B(previousX) B(PBLeft)
     */
    private void deleteFixup(Node x, Node xParent) {
        while (x != this.root && (x == null || x.getColor() == BLACK)) {
            // 情况3 被删除的子节点 x 的颜色是黑色
            Node brother;
            if (x == xParent.getLeft()) {
                // x 是左节点，也就是我们上述讨论的情况
                brother = xParent.getRight();
                if (brother == null) {
                    throw new RuntimeException("x's sibling should not be null");
                }

                if (brother.getColor() == RED) {
                    // 情况 3.2，只要我们将 brother 变为黑色，parent 变为红色，然后以 parent 为中心左旋将转化为情况 3.1
                    brother.setColor(BLACK);
                    xParent.setColor(RED);
                    this.leftRotate(xParent);
                    brother = xParent.getRight();
                }

                if ((brother.getLeft() == null || brother.getLeft().getColor() == BLACK)
                    && (brother.getRight() == null || brother.getRight().getColor() == BLACK)) {
                    // 情况 3.1.1 将兄弟节点置为红色，然后递归的处理 parent 节点
                    brother.setColor(RED);
                    x = xParent;
                    xParent = xParent.getParent();
                } else {
                    if (brother.getRight() == null || brother.getRight().getColor() == BLACK) {
                        // 情况 3.1.3 brother 左节点为红右节点为黑，这时候我们将 brother 节点变为红色，brother 的左节点变为黑色，
                        // 然后以 brother 为中心右旋，成功将情况转化为 3.1.2
                        brother.getLeft().setColor(BLACK);
                        brother.setColor(RED);
                        this.rightRotate(brother);
                        brother = xParent.getRight();
                    }
                    // 情况3.1.2 brother 的右节点为红色，将 brother节点变为 parent 的颜色，然后将 parent 节点变为黑色
                    // brother 的右子节点变为黑色，然后我们以 parent 为中心左旋，x 节点的黑高 +1 并且 brother 子节点黑高不变
                    // 将 x 赋值为 root 令循环结束
                    brother.setColor(xParent.getColor());
                    xParent.setColor(BLACK);
                    if (brother.getRight() != null) {
                        brother.getRight().setColor(BLACK);
                    }

                    this.leftRotate(xParent);
                    x = this.root;
                    xParent = x.getParent();
                }
            } else {
                // x 是右节点，只需要涉及到左右方向的过程都反转即可
                brother = xParent.getLeft();
                if (brother == null) {
                    throw new RuntimeException("x's sibling should not be null");
                }

                if (brother.getColor() == RED) {
                    // 情况 3.2，只要我们将 brother 变为黑色，parent 变为红色，然后以 parent 为中心右旋将转化为情况 3.1
                    brother.setColor(BLACK);
                    xParent.setColor(RED);
                    this.rightRotate(xParent);
                    brother = xParent.getLeft();
                }

                if ((brother.getRight() == null || brother.getRight().getColor() == BLACK)
                    && (brother.getLeft() == null || brother.getLeft().getColor() == BLACK)) {
                    // 情况 3.1.1 将兄弟节点置为红色，然后递归的处理 parent 节点
                    brother.setColor(RED);
                    x = xParent;
                    xParent = xParent.getParent();
                } else {
                    if (brother.getLeft() == null || brother.getLeft().getColor() == BLACK) {
                        // 情况 3.1.3 brother 右节点为红左节点为黑，这时候我们将 brother 节点变为红色，brother 的右节点变为黑色，
                        // 然后以 brother 为中心左旋，成功将情况转化为 3.1.2
                        brother.getRight().setColor(BLACK);
                        brother.setColor(RED);
                        this.leftRotate(brother);
                        brother = xParent.getLeft();
                    }
                    // 情况3.1.2 brother 的左节点为红色，将 brother节点变为 parent 的颜色，然后将 parent 节点变为黑色
                    // brother 的左子节点变为黑色，然后我们以 parent 为中心右旋，x 节点的黑高 +1 并且 brother 子节点黑高不变
                    // 将 x 赋值为 root 令循环结束
                    brother.setColor(xParent.getColor());
                    xParent.setColor(BLACK);
                    if (brother.getLeft() != null) {
                        brother.getLeft().setColor(BLACK);
                    }

                    this.rightRotate(xParent);
                    x = this.root;
                    xParent = x.getParent();
                }
            }
        }
        // 情况1 + 情况2: 走到这说明待处理节点 x 是红节点或者根节点，将 x 变为黑色即可
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
