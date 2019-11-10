package com.study.arithmetic.Day8_haffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class HaffmanTree<T> {
    TreeNode<T> root;

    public TreeNode<T> createHaffmanTree(ArrayList<TreeNode> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            TreeNode<T> leftNode = list.get(list.size() - 1);
            TreeNode<T> rightNode = list.get(list.size() - 2);
            TreeNode parentNode = new TreeNode(TreeNode.HAFFMANCALCUDATA, leftNode.weight + rightNode.weight);
            parentNode.leftChild = leftNode;
            leftNode.parentNode = parentNode;
            parentNode.rightChild = rightNode;
            rightNode.parentNode = parentNode;
            list.add(parentNode);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        this.root = list.get(0);
        return this.root;
    }

    /**
     * 将Haffman树一行一行打印
     * 通过队列的方式
     */
    public void showHaffmanTree() {
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        list.offer(this.root);
        while (!list.isEmpty()) {
            TreeNode<T> node = list.poll();
            if (!node.data.equals(TreeNode.HAFFMANCALCUDATA)) {
                System.out.println(node.data);
            }
            if (node.leftChild != null) {
                list.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                list.offer(node.rightChild);
            }
        }
    }

    /**
     * 获取当前结点的哈夫曼编码 左边是0，右边是1  从根开始算
     * @param treeNode
     * @return
     */
    public String getCode(TreeNode<T> treeNode) {
        Stack<Integer> codeStack = new Stack<>();
        while (treeNode != null && treeNode.parentNode != null) {
            TreeNode<T> parentNode = treeNode.parentNode;
            if (parentNode.leftChild == treeNode) {
                codeStack.push(0);
            } else if (parentNode.rightChild == treeNode) {
                codeStack.push(1);
            }
            treeNode = treeNode.parentNode;
        }
        StringBuilder sb = new StringBuilder();
        while (!codeStack.empty()) {
            sb.append(codeStack.pop());
        }
        return sb.toString();
    }

    public static class TreeNode<T> implements Comparable<TreeNode<T>> {
        T data;
        int weight;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parentNode;
        static String HAFFMANCALCUDATA = "p";

        public TreeNode(T data, int weight) {
            this.data = data;
            this.weight = weight;
            this.leftChild = null;
            this.rightChild = null;
            this.parentNode = null;
        }

        @Override
        public int compareTo(TreeNode<T> o) {
            if (this.weight > o.weight) {
                return -1;
            } else if (this.weight < o.weight) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }
    }
}
