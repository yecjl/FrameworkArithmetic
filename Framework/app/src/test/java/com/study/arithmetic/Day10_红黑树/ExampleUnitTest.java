package com.study.arithmetic.Day10_红黑树;

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
        int[] list = new int[]{11, 2, 1, 7, 5, 8, 4, 14, 15};
        RedBlackTree<Integer, Integer> tree = new RedBlackTree();
        for (int i : list) {
            tree.put(i, i);
        }
        showHaffmanTree(tree);
    }

    /**
     * 将Haffman树一行一行打印
     * 通过队列的方式
     */
    public void showHaffmanTree(RedBlackTree<Integer, Integer> tree) {
        LinkedList<RedBlackTree.TreeMapEntry<Integer, Integer>> list = new LinkedList<>();
        list.offer(tree.getRoot());
        while (!list.isEmpty()) {
            RedBlackTree.TreeMapEntry<Integer, Integer> node = list.poll();
            System.out.println(node.toString());
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }
}