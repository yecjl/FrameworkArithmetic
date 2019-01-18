package com.study.arithmetic.binarytree;

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
        int[] list = new int[]{31, 68, 45, 90, 23, 39, 54, 68, 87, 76};
        SearchBinaryTree<Integer> tree = new SearchBinaryTree<>();
        for (int i : list) {
            tree.put(i);
        }
        tree.searchLeftMidRight(tree.root);
    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}