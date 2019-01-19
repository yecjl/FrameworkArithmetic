package com.study.arithmetic.Day1.card;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CardsTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCards() {
        Cards[] cards = new Cards[]{
                new Cards(2, 3),
                new Cards(3, 7),
                new Cards(8, 2),
                new Cards(1, 4),
                new Cards(7, 9)
        };
//         Arrays.sort(cards); // 实际上用的是binarySort + 递归 + 分开截断，至少执行了100行代码以上
        bubbleSort(cards);
        printList(cards);
    }

    /**
     * 冒泡排序
     *
     * @param list
     */
    public void bubbleSort(Cards[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    Cards temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public void printList(Cards[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + "\n");
        }
        System.out.println("\n---------");
    }
}