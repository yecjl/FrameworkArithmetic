package com.study.arithmetic.Day8_哈夫曼树;

import org.junit.Test;

import java.util.ArrayList;

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
        ArrayList<HaffmanTree.TreeNode> treeNodes = new ArrayList<>();
        HaffmanTree.TreeNode node1 = new HaffmanTree.TreeNode("C", 50);
        HaffmanTree.TreeNode node2 = new HaffmanTree.TreeNode("A", 10);
        HaffmanTree.TreeNode node3 = new HaffmanTree.TreeNode("B", 20);
        HaffmanTree.TreeNode node4 = new HaffmanTree.TreeNode("D", 60);
        HaffmanTree.TreeNode node5 = new HaffmanTree.TreeNode("E", 70);
        HaffmanTree.TreeNode node6 = new HaffmanTree.TreeNode("F", 200);
        treeNodes.add(node1);
        treeNodes.add(node2);
        treeNodes.add(node3);
        treeNodes.add(node4);
        treeNodes.add(node5);
        treeNodes.add(node6);
        HaffmanTree<String> haffmanTree = new HaffmanTree<>();
        HaffmanTree.TreeNode<String> root = haffmanTree.createHaffmanTree(treeNodes);
        haffmanTree.showHaffmanTree();
        System.out.println(node3.data + "'s code = " + haffmanTree.getCode(node3));
    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}