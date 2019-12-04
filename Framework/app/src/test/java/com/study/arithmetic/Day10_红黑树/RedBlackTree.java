package com.study.arithmetic.Day10_红黑树;

import java.util.Comparator;
import java.util.Map;

public class RedBlackTree<K, V> {
    private transient TreeMapEntry<K, V> root;
    private final Comparator<? super K> comparator;
    private transient int size = 0;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
        comparator = null;
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public TreeMapEntry<K, V> getRoot() {
        return root;
    }

    public V put(K key, V value) {
        TreeMapEntry<K, V> t = root;
        if (t == null) {
            root = new TreeMapEntry<>(key, value, null);
            size = 1;
            return null;
        }
        // 1、插入节点：先按照二叉排序树的方式插入一个节点（红色）
        int cmp;
        TreeMapEntry<K, V> parent;
        Comparator<? super K> cpr = comparator;
        // 1.1、找到要插入的位置
        if (cpr != null) {
            // 如果有比较器
            do {
                parent = t;
                cmp = cpr.compare(key, t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t.setValue(value);
                }
            } while (t != null);
        } else {
            if (key == null) {
                throw new NullPointerException();
            }
            // 如果没有比较器，通过key的比较器
            Comparable<? super K> k = (Comparable<? super K>) key;
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t.setValue(value);
                }
            } while (t != null);
        }
        // 1.2、插入到二叉排序树中
        TreeMapEntry<K, V> e = new TreeMapEntry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = e;
        } else {
            parent.right = e;
        }
        fixAfterInsertion(e);
        size++;
        return null;
    }

    /**
     * 调整红黑树
     *
     * @param x
     */
    private void fixAfterInsertion(TreeMapEntry<K, V> x) {
        // 1.3、将插入的节点涂红
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            // 4、父节点是祖父节点的左孩子
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // 情况1：祖父节点的另一个子节点（叔叔节点）是红色
                TreeMapEntry<K, V> y = rightOf(parentOf(parentOf(x))); // 叔叔节点
                if (colorOf(y) == RED) {
                    // 对策：将当前节点的父节点和叔叔节点涂黑，祖父节点涂红，把当前节点指向祖父节点，从新的当前节点开始算法
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        // 情况2.1：当前节点是其父节点的右孩子
                        // 对策：当前节点的父节点做为新的当前节点，以新当前节点为支点左旋。
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    // 情况2.2：当前节点是其父节点的左孩子
                    // 对策：父节点变为黑色，祖父节点变红色，再祖父节点为支点进行右旋
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            }
            // 5、父节点是祖父节点的右孩子
            else {
                // 情况1：祖父节点的另一个子节点（叔叔节点）是红色
                TreeMapEntry<K, V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    // 对策：将当前节点的父节点和叔叔节点涂黑，祖父节点涂红，把当前节点指向祖父节点，从新的当前节点开始算法
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        // 情况2.1：当前节点是其父节点的左孩子
                        // 对策：当前节点的父节点做为新的当前节点，以新当前节点为支点右旋。
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    // 情况2.2：当前节点是其父节点的右孩子
                    // 对策：父节点变为黑色，祖父节点变红色，再祖父节点为支点进行左旋
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    leftOf(parentOf(parentOf(x)));
                }
            }
        }
        // 2、插入的是根节点，解决：直接将节点涂黑
        // 3、插入的节点的父节点是黑色，不处理
        root.color = BLACK;
    }

    public V remove(Object key) {
        TreeMapEntry<K, V> p = getEntry(key);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }

    private void deleteEntry(TreeMapEntry<K, V> p) {
        size--;

        //删除节点：先进行二叉排序树的删除操作，然后已替换节点作为当前节点进行后面的平衡操作
        // 1、如果有左右孩子，查找到当前节点的后继节点，将值替换，然后删除后继节点
        if (p.left != null && p.right != null) {
            TreeMapEntry<K, V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s; // p 指向了s/
        }

        TreeMapEntry<K, V> replacement = p.left != null ? p.left : p.right;
        if (replacement != null) {
            // 2、如果只有左孩子，或者右孩子，将后面的节点顶上
            replacement.parent = p.parent;
            if (p.parent == null) {
                root = replacement;
            } else if (p.parent.left == p) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
            p.left = p.right = p.parent = null;

            //3、当前节点x是黑色，需要处理
            if (colorOf(p) == BLACK) {
                fixAfterDeletion(replacement);
            }
        } else if (p.parent == null) {
            // p没有叶子节点，并且p为root
            root = null;
        } else {
            // p没有叶子节点
            if (colorOf(p) == BLACK) {
                fixAfterDeletion(p);
            }

            if (p.parent != null) {
                if (p.parent.left == p) {
                    p.parent.left = null;
                } else if (p.parent.right == p) {
                    p.parent.right = null;
                }
                p.parent = null;
            }
        }
    }

    /**
     * 获取当前节点的后继节点
     * @param t
     * @return
     */
    private TreeMapEntry<K, V> successor(TreeMapEntry<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.right != null) {
            TreeMapEntry<K, V> p = t.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            TreeMapEntry<K, V> p = t.parent;
            TreeMapEntry<K, V> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private void fixAfterDeletion(TreeMapEntry<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            // 被删除节点是父节点的左孩子
            if (x == leftOf(parentOf(x))) {
                //1、 当前节点x的兄弟节点是红色(此时父节点和兄弟节点的子节点分为黑)
                TreeMapEntry<K, V> sib = rightOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    // 对策：把父节点染成红色，兄弟节点染成黑色，对父节点进行左旋，重新设置x的兄弟节点
                    setColor(parentOf(x), RED);
                    setColor(sib, BLACK);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                // 2、 当前节点x 的兄弟节点是黑色
                if (colorOf(sib) == BLACK) {
                    // 2.1 兄弟节点的两个孩子都是黑色
                    if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                        // 对策：将x的兄弟节点设为红色，设置x的父节点为新的x节点
                        setColor(sib, RED);
                        x = parentOf(x);
                    } else if (colorOf(rightOf(sib)) == BLACK && colorOf(leftOf(sib)) == RED) {
                        // 2.2 兄弟的右孩子是黑色，左孩子是红色
                        // 对策：将x兄弟节点的左孩子设为黑色，将x兄弟节点设置红色，将x的兄弟节点右旋，右旋后，重新设置x的兄弟节点。
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    } else if (colorOf(rightOf(sib)) == RED) {
                        // 2.3 兄弟节点的右孩子是红色
                        // 对策：把兄弟节点染成当前节点父节点颜色，把当前节点父节点染成黑色，兄弟节点右孩子染成黑色，再以当前节点的父节点为支点进行左旋，算法结束
                        setColor(sib, colorOf(parentOf(x)));
                        setColor(parentOf(x), BLACK);
                        setColor(rightOf(sib), BLACK);
                        rotateLeft(parentOf(x));
                        x = root;
                    }
                }
            }
            // 被删除节点是父节点的右孩子
            else {
                TreeMapEntry<K,V> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    private TreeMapEntry<K, V> getEntry(Object key) {
        if (comparator != null) {
            return getEntryUsingComparator(key);
        }
        if (key == null) {
            throw new NullPointerException();
        }
        Comparable<? super K> k = (Comparable<? super K>) key;
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            int cmp = k.compareTo(p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private TreeMapEntry<K, V> getEntryUsingComparator(Object key) {
        K k = (K) key;
        Comparator<? super K> cpr = this.comparator;
        if (cpr != null) {
            TreeMapEntry<K, V> p = this.root;
            while (p != null) {
                int cmp = cpr.compare(k, p.key);
                if (cmp < 0) {
                    p = p.left;
                } else if (cmp > 0) {
                    p = p.right;
                } else {
                    return p;
                }
            }
        }
        return null;
    }

    private void rotateLeft(TreeMapEntry<K, V> p) {
        if (p != null) {
            TreeMapEntry<K, V> r = p.right;
            // 1、把 p 的 右孩子（r）的左孩子作为 p 的 右孩子
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            // 2、把 r 移动到 p 的位置
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            } else {
                if (p.parent.left == p) {
                    p.parent.left = r;
                } else {
                    p.parent.right = r;
                }
            }
            // 3、把 p 最为 r 的左孩子
            r.left = p;
            p.parent = r;
        }
    }

    public void rotateRight(TreeMapEntry<K, V> p) {
        if (p != null) {
            TreeMapEntry<K, V> l = p.left;
            // 1、把 p 的 左孩子（l）的右孩子作为 p 的 左孩子
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            // 2、把 l 移动到 p 的位置
            l.parent = p.parent;
            if (p.parent == null) {
                root = l;
            } else {
                if (p.parent.left == p) {
                    p.parent.left = l;
                } else {
                    p.parent.right = l;
                }
            }
            // 3、把 p 最为 l 的右孩子
            l.right = p;
            p.parent = l;
        }
    }

    private static <K, V> void setColor(TreeMapEntry<K, V> p, boolean color) {
        if (p != null) {
            p.color = color;
        }
    }

    private static <K, V> boolean colorOf(TreeMapEntry<K, V> p) {
        return p == null ? BLACK : p.color;
    }

    private static <K, V> TreeMapEntry<K, V> leftOf(TreeMapEntry<K, V> p) {
        return p == null ? null : p.left;
    }

    private static <K, V> TreeMapEntry<K, V> rightOf(TreeMapEntry<K, V> p) {
        return p == null ? null : p.right;
    }

    private static <K, V> TreeMapEntry<K, V> parentOf(TreeMapEntry<K, V> p) {
        return p == null ? null : p.parent;
    }

    static final class TreeMapEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        TreeMapEntry<K, V> left;
        TreeMapEntry<K, V> right;
        TreeMapEntry<K, V> parent;
        boolean color = BLACK;

        public TreeMapEntry(K key, V value, TreeMapEntry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry<?, ?>)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return valEquals(key, e.getKey()) && valEquals(value, e.getValue());
        }

        @Override
        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        @Override
        public String toString() {
            return key + "=" + value + "=" + (color == BLACK ? "black" : "red");
        }
    }

    /**
     * Test two values for equality.  Differs from o1.equals(o2) only in
     * that it copes with {@code null} o1 properly.
     */
    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }
}
