package com.study.arithmetic.Day6_binarytree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSort() {
        int[] list = new int[]{31, 68, 45, 90, 23, 39, 54, 87, 76, 80, 81, 82, 100, 40, 41};
        SearchBinaryTree tree = new SearchBinaryTree();
        for (int i : list) {
            tree.put(i);
        }
        tree.midOrderTraverse(tree.root);
        System.out.println("\n---------");
        for (int i = 0; i < list.length; i++) {
            System.out.println("delete data = " + list[i]);
            SearchBinaryTree.TreeNode treeNode = tree.searchNode(list[i]);
            if (treeNode == null) {
                System.out.println("don't find this data");
            } else {
                System.out.println("find this data: " + treeNode.data);
            }

            tree.deleteNode(treeNode);
            tree.midOrderTraverse(tree.root);
            System.out.println("\n---------");
        }

//        System.out.println("delete data = " + 81);
//        SearchBinaryTree.TreeNode treeNode = tree.searchNode(81);
//        if (treeNode == null) {
//            System.out.println("don't find this data");
//        } else {
//            System.out.println("find this data: " + treeNode.data);
//        }
//
//        tree.deleteNode(treeNode);
//        tree.midOrderTraverse(tree.root);
//        System.out.println("\n---------");

    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}