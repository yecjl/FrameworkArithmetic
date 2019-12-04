package com.study.arithmetic.Day4_二叉树;

/**
 * 功能：二叉树
 * <p>
 * Created by danke on 2018/12/3.
 */
public class BinaryTree<E> {
    transient Node<E> root; // 树的根结点

    public BinaryTree(E e) {
        this.root = new Node<>(e, null, null);
    }

    /**
     * 创建二叉树
     * @param root
     */
    public void createTree(Node<String> root) {
        // 创建每个结点
        Node<String> bNode = new Node<>("B", null, null);
        Node<String> cNode = new Node<>("C", null, null);
        Node<String> dNode = new Node<>("D", null, null);
        Node<String> eNode = new Node<>("E", null, null);
        Node<String> fNode = new Node<>("F", null, null);
        Node<String> gNode = new Node<>("G", null, null);
        Node<String> hNode = new Node<>("H", null, null);
        Node<String> jNode = new Node<>("J", null, null);
        Node<String> iNode = new Node<>("I", null, null);
        // 设置每个结点的左右孩子
        root.leftChild = bNode;
        root.rightChild = cNode;
        bNode.leftChild = dNode;
        dNode.leftChild = gNode;
        dNode.rightChild = hNode;
        hNode.leftChild = iNode;
        cNode.leftChild = eNode;
        cNode.rightChild = fNode;
        eNode.rightChild = jNode;
    }

    /**
     * 中序遍历 LDR
     * @param root
     */
    public void midOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild); // 左
        System.out.print(root.data); // 根
        midOrderTraverse(root.rightChild); // 右
    }

    /**
     * 前序遍历 DLR
     * @param root
     */
    public void preOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data); // 根
        preOrderTraverse(root.leftChild); // 左
        preOrderTraverse(root.rightChild); // 右
    }

    /**
     * 后序遍历 LRD
     * @param root
     */
    public void postOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.leftChild); // 左
        postOrderTraverse(root.rightChild); // 右
        System.out.print(root.data); // 根
    }

    public class Node<E> {
        E data;
        Node<E> leftChild; // 左孩子
        Node<E> rightChild; // 右孩子

        public Node(E data, Node<E> leftChild, Node<E> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

}
