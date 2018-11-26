package com.study.arithmetic;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

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
        int[] list = new int[]{2, 1, 5, 4, 3, 9, 6};
        printList(list);
        selectSort(list);
//        bubbleSort(list);
//        Arrays.sort(list);
        printList(list);
    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }

    /**
     * 冒泡排序
     * @param list
     */
    public void bubbleSort(int[] list) {
        // (n-1) + (n-2) + (n-3) + ... + 1 = ((n-1) * ((n-1) + 1 ) / 2 = n * (n-1) / 2
        for (int i = list.length - 1; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                //2, 1, 5, 4, 3, 9, 6 (原)
                //2, 1, 5, 4, 3, 6, 9
                //2, 1, 3, 4, 5, 6, 9
                //1, 2, 3, 4, 5, 6, 9
                //1, 2, 3, 4, 5, 6, 9 // flag = true
                break;
            }
        }
    }

    /**
     * 选择排序
     * @param list
     */
    public void selectSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int index = i;
            for (int j = index + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    index = j;
                }
            }
            if (i != index) {
                int temp = list[i];
                list[i] = list[index];
                list[index] = temp;
            }
        }
    }
}