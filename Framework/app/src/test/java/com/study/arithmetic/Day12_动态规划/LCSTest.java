package com.study.arithmetic.Day12_动态规划;


import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LCSTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        String str1 = "bdcaba";
        String str2 = "abcbdab";

        getLCS(str1, str2);
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public void getLCS(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] result = new int[chars2.length + 1][chars1.length + 1];
        // 将第一行，第一列填满0
        for (int i = 0; i < result[0].length; i++) {
            result[0][i] = 0;
        }
        for (int i = 0; i < result.length; i++) {
            result[i][0] = 0;
        }

        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[i].length; j++) {
                // 如果 两个字符相等，那么result 等于左上角 + 1
                if (chars2[i - 1] == chars1[j - 1]) {
                    result[i][j] = result[i - 1][j - 1] + 1;
                } else {
                    result[i][j] = max(result[i][j - 1], result[i - 1][j]);
                }
            }
        }

        System.out.print("    ");
        for (int i = 0; i < result[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("      ");
        for (int i = 0; i < chars1.length; i++) {
            System.out.print(chars1[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < result.length; i++) {
            System.out.print(i + " ");
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(chars2[i - 1] + " ");
            }

            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        Stack stack = new Stack();
        int i = result.length - 1;
        int j = result[0].length - 1;
        while (i != 0 && j != 0) {
            System.out.println("i = " + i + ", j = " + j + ", result = " + chars1[j - 1] + "/" + chars2[i - 1]);
            if (chars1[j - 1] == chars2[i - 1]) {
                System.out.println("相同，入栈 = " + chars1[j - 1]);
                stack.push(chars1[j - 1]);
                i--;
                j--;
            } else {
                if (result[i][j - 1] >= result[i][j]) {
                    j--;
                    System.out.println("j-- = " + j);
                } else {
                    i--;
                    System.out.println("i-- = " + i);
                }
            }
        }

        System.out.println("--------------");
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
    }
}