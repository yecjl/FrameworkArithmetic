package com.study.arithmetic.Day4;

/**
 * 功能：
 * <p>
 * Created by danke on 2018/12/3.
 */
public class BinarayTree<E> {
    transient Node<E> root;

    public BinarayTree(E e) {
        this.root = new Node<>(e, null, null);
    }

    public void creatTree(Node<String> root) {
        Node<String> bNode = new Node<>("B", null, null);
        Node<String> cNode = new Node<>("C", null, null);
        Node<String> dNode = new Node<>("D", null, null);
        Node<String> eNode = new Node<>("E", null, null);
        Node<String> fNode = new Node<>("F", null, null);
        Node<String> gNode = new Node<>("G", null, null);
        Node<String> hNode = new Node<>("H", null, null);
        Node<String> jNode = new Node<>("J", null, null);
        Node<String> iNode = new Node<>("I", null, null);
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
        midOrderTraverse(root.leftChild);
        System.out.print(root.data);
        midOrderTraverse(root.rightChild);
    }

    /**
     * 前序遍历 DLR
     * @param root
     */
    public void preOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data);
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    /**
     * 后序遍历 LRD
     * @param root
     */
    public void postOrderTraverse(Node<E> root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.print(root.data);
    }

    public class Node<E> {
        E data;
        Node<E> leftChild;
        Node<E> rightChild;

        public Node(E data, Node<E> leftChild, Node<E> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

}
