package com.study.arithmetic.Day9_AVL平衡二叉树树;

/**
 * 平衡二叉树（基于排序二叉树）
 *
 * @param <E>
 */
public class AVLBTree<E extends Comparable<E>> {
    Node<E> root;
    int size;
    private static final int LEFT_HIGH = 1;
    private static final int RIGHT_HIGH = -1;
    private static final int EQUAL_HIGH = 0;

    public void left_rotate(Node<E> x) {
        if (x == null) return;
        Node<E> parent = x.parent;
        Node<E> y = x.rightChild;
        if (y == null) return;
        // 1、把 x 的 右孩子（y）的左孩子作为 x 的 右孩子
        x.rightChild = y.leftChild;
        if (y.leftChild != null) {
            y.leftChild.parent = x;
        }
        // 2、把 y 移动到 x 的位置
        if (parent == null) {
            y.parent = null;
            this.root = y;
        } else {
            if (parent.leftChild == x) {
                parent.leftChild = y;
                y.parent = parent;
            } else if (parent.rightChild == x) {
                parent.rightChild = y;
                y.parent = parent;
            }
        }
        // 3、把 x 最为 y 的左孩子
        y.leftChild = x;
        x.parent = y;
    }

    public void right_rotate(Node<E> x) {
        if (x == null) return;
        Node<E> parent = x.parent;
        Node<E> y = x.leftChild;
        if (y == null) return;
        // 1、把 x 的 左孩子（y）的右孩子作为 x 的 左孩子
        x.leftChild = y.rightChild;
        if (y.rightChild != null) {
            y.rightChild.parent = x;
        }
        // 2、把 y 移动到 x 的位置
        if (parent == null) {
            y.parent = null;
            this.root = y;
        } else {
            if (parent.leftChild == x) {
                parent.leftChild = y;
                y.parent = parent;
            } else if (parent.rightChild == x) {
                parent.rightChild = y;
                y.parent = parent;
            }
        }
        // 3、把 x 最为 y 的右孩子
        y.rightChild = x;
        x.parent = y;
    }

    /**
     * 左平衡：左边太重了
     *
     * @param t
     */
    public void leftBalance(Node<E> t) {
        Node<E> tl = t.leftChild;
        if (tl.balance == LEFT_HIGH) {
            // 1、如果新的结点插入到t的左孩子的左子树中，则直接进行右旋操作
            right_rotate(t);
            tl.balance = EQUAL_HIGH;
            t.balance = EQUAL_HIGH;
        } else if (tl.balance == RIGHT_HIGH) {
            // 2、如果新的结点插入到t的左孩子的右子树中，则需要进行分情况
            Node<E> tlr = tl.rightChild;
            left_rotate(tl);
            right_rotate(t);
            switch (tlr.balance) {
                case RIGHT_HIGH:
                    tlr.balance = EQUAL_HIGH;
                    tl.balance = LEFT_HIGH;
                    t.balance = EQUAL_HIGH;
                    break;
                case LEFT_HIGH:
                    tlr.balance = EQUAL_HIGH;
                    tl.balance = EQUAL_HIGH;
                    t.balance = RIGHT_HIGH;
                    break;
                case EQUAL_HIGH:
                    tlr.balance = EQUAL_HIGH;
                    tl.balance = EQUAL_HIGH;
                    t.balance = EQUAL_HIGH;
                    break;
            }
        }
    }

    /**
     * 右平衡
     *
     * @param t
     */
    public void rightBalance(Node<E> t) {
        Node<E> tr = t.rightChild;
        if (tr.balance == RIGHT_HIGH) {
            // 1、如果新的结点插入到t的右孩子的右子树中，则直接进行左旋操作
            left_rotate(t);
            tr.balance = EQUAL_HIGH;
            t.balance = EQUAL_HIGH;
        } else if (tr.balance == LEFT_HIGH) {
            // 2、如果新的结点插入到t的右孩子的左子树中，则需要进行分情况
            Node<E> trl = tr.leftChild;
            right_rotate(tr);
            left_rotate(t);
            switch (trl.balance) {
                case LEFT_HIGH:
                    trl.balance = EQUAL_HIGH;
                    tr.balance = RIGHT_HIGH;
                    t.balance = EQUAL_HIGH;
                    break;
                case RIGHT_HIGH:
                    trl.balance = EQUAL_HIGH;
                    tr.balance = EQUAL_HIGH;
                    t.balance = LEFT_HIGH;
                    break;
                case EQUAL_HIGH:
                    trl.balance = EQUAL_HIGH;
                    tr.balance = EQUAL_HIGH;
                    t.balance = EQUAL_HIGH;
                    break;
            }
        }
    }

    public boolean insertElement(E elem) {
        if (this.root == null) {
            this.root = new Node<>(elem, null);
        } else {
            Node<E> node = this.root;
            Node<E> parent = null;
            Comparable<? super E> comparable = elem; // 能够比大小
            int compareTo = 0;
            // 查找节点可以放置的位置
            while (node != null) {
                parent = node;
                compareTo = comparable.compareTo(node.elem);
                if (compareTo < 0) {
                    node = node.leftChild;
                } else if (compareTo > 0) {
                    node = node.rightChild;
                } else {
                    return false;
                }
            }
            // 将节点放置在树上面
            Node<E> newNode = new Node<>(elem, parent);
            if (compareTo < 0) {
                parent.leftChild = newNode;
            } else if (compareTo > 0) {
                parent.rightChild = newNode;
            }
            // 修改平衡因子，从插入的当前的叶子节点开始查找，非平衡的子树，通过回溯的方法
            while (parent != null) {
                compareTo = comparable.compareTo(parent.elem);
                if (compareTo < 0) {
                    parent.balance++;
                } else if (compareTo > 0) {
                    parent.balance--;
                }
                if (compareTo == 0) {
                    break;
                }
                if (parent.balance == 2 || parent.balance == -2) { // 性能要比Math.abs(parent.balance) == 2 好
                    // 出现平衡问题，需要立马修正
                    fixAfterInsertion(parent);
                } else {
                    // 继续往上查找
                    parent = parent.parent;
                }
            }
        }
        size++;
        return true;
    }

    private void fixAfterInsertion(Node<E> node) {
        if (node.balance == 2) {
            leftBalance(node);
        }
        if (node.balance == -2) {
            rightBalance(node);
        }
    }

    public static class Node<E extends Comparable<E>> {
        E elem;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;
        int balance = EQUAL_HIGH; // 平衡因子：左边减右边

        public Node(E elem, Node<E> parent) {
            this.elem = elem;
            this.parent = parent;
        }
    }

}
