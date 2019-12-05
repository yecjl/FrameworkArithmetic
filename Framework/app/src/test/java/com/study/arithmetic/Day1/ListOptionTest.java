package com.study.arithmetic.Day1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListOptionTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSearch() {
        int[] list = new int[]{2, 1, 5, 4, 3, 9, 6};
        printList(list);
//        int search = search(array, 3);
//        System.out.println("search = " + search);
//        modify(array, 2, 8);
        delete(list, 2);
        printList(list);

    }

    public int search(int[] list, int des) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == des) {
                return i;
            }
        }
        return -1;
    }

    public void modify(int[] list, int index, int des) {
        list[index] = des;
    }

    public void instert(int[] list, int index, int des) {
        for (int i = list.length - 1; i > index; i--) {
            list[i] = list[i-1];
        }
        list[index] = des;
    }

public void delete(int[] list, int index) {
    for (int i = index; i < list.length - 1; i++) {
        list[i] = list[i+1];
    }
    list[list.length - 1] = 0;
}

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}