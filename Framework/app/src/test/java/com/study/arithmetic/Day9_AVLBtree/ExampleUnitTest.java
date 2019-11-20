package com.study.arithmetic.Day9_AVLBtree;

import org.junit.Test;

import java.util.LinkedList;

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
        int[] list = new int[]{5, 8, 2, 0, 1, -2};
        AVLBTree<Integer> tree = new AVLBTree();
        for (int i : list) {
            tree.insertElement(i);
        }
        showHaffmanTree(tree);
    }

    /**
     * 将Haffman树一行一行打印
     * 通过队列的方式
     */
    public void showHaffmanTree(AVLBTree<Integer> tree) {
        LinkedList<AVLBTree.Node<Integer>> list = new LinkedList<>();
        list.offer(tree.root);
        while (!list.isEmpty()) {
            AVLBTree.Node<Integer> node = list.poll();
            System.out.print(node.elem + " ");
            if (node.leftChild != null) {
                list.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                list.offer(node.rightChild);
            }
        }
    }
}