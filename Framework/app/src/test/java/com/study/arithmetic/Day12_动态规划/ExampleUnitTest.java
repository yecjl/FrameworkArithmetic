package com.study.arithmetic.Day12_动态规划;


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
    public void test() {
        System.out.println("fn = " + fn1(100));
        System.out.println("fn = " + fn2(100));
    }

    /**
     * n + (n -1) + ... + 1
     *
     * @param n
     * @return
     */
    public int fn1(int n) {
        if (n == 1) {
            return 1;
        }
        return n + fn1(n - 1);
    }

    /**
     * 动态规划计算
     * @param n
     * @return
     */
    public int fn2(int n) {
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] + i + 1;
        }
        return result[n - 1];
    }
}