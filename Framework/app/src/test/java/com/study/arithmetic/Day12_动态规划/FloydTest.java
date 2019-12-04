package com.study.arithmetic.Day12_动态规划;


import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FloydTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        floyd();
        printArray(d);
        System.out.println("==========");
        printArray(p);
        System.out.println("==========");
        printShortPath();
    }

    private void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static final int I = 100;
    //邻接距阵
    public static int[][] d = new int[][]{
            {0, 2, 1, 5},
            {2, 0, 4, I},
            {1, 4, 0, 3},
            {5, I, 3, 0}
    };
    public static int[][] p = new int[][]{
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3}
    };

    public void floyd() {
        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d.length; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        p[i][j] = p[i][k];
                    }
                }
            }
        }
    }

    public static void printShortPath() {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                System.out.print("v" + i + "--> v" + j + ", weight = " + d[i][j] + " ");
                if (p[i][j] != j) {
                    System.out.print(", short path = ");
                    int k = i;
                    LinkedList list = new LinkedList();
                    list.add(i);
                    while (p[k][j] != k) {
                        k = p[k][j];
                        list.add(k);
                    }
                    while (!list.isEmpty()) {
                        if (list.size() == 1) {
                            System.out.print("v" + list.poll());
                        } else {
                            System.out.print("v" + list.poll() + "-->");
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    public static int getPath(int i, int j) {
        if (p[i][j] != j) {
            return p[i][j];
        }
        return -1;
    }




}