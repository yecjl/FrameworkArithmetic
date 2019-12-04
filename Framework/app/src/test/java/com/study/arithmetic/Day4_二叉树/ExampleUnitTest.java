package com.study.arithmetic.Day4_二叉树;

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
    public void tesTraverse() {
        BinaryTree<String> tree = new BinaryTree<>("A");
        tree.createTree(tree.root);
        // DLR
//        tree.preOrderTraverse(tree.root);
//        // LDR
//        tree.midOrderTraverse(tree.root);
        // LRD
        tree.postOrderTraverse(tree.root);
    }



}