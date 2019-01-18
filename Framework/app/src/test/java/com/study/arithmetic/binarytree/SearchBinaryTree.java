package com.study.arithmetic.binarytree;

import java.util.NoSuchElementException;

/**
 * 功能：
 * <p>
 * Created by danke on 2018/12/7.
 */
public class SearchBinaryTree<T> {

    public TreeNode<T> root;

    public void put(T data) {
        if (root == null) {
            root = new TreeNode<>(data, null, null, null);
            return;
        }
        TreeNode<T> node = root;
        TreeNode<T> parent = null;
        while (node != null) {
            parent = node;
//            if (data > node.data) {
//
//            }
        }
    }

    public void del(TreeNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        TreeNode<T> parent = node.parent;
        if (node.leftChild == null && node.rightChild == null) {

        } else if (node.leftChild != null && node.rightChild == null) {

        } else if (node.leftChild == null && node.rightChild != null) {

        }
    }

    public void searchLeftMidRight(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        searchLeftMidRight(root.leftChild);
        System.out.print(root.data + " ");
        searchLeftMidRight(root.rightChild);
    }

    public static class TreeNode<T> {
        T data;
        TreeNode<T> leftChild;
        TreeNode<T> rightChild;
        TreeNode<T> parent;

        public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild, TreeNode<T> parent) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }
    }

}
