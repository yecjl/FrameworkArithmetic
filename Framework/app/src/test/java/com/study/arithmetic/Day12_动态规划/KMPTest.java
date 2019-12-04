package com.study.arithmetic.Day12_动态规划;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class KMPTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        String str1 = "ababcabcbababcabacaba";
        String str2 = "ababcaba"; // 模式串

        int[] kmpNext = getKMPNext(str2);
        for (int i = 0; i < kmpNext.length; i++) {
            System.out.print(kmpNext[i]);
        }
        System.out.println();
        int kmpIndex = getKMPIndex(str1, str2, kmpNext);
        System.out.println(kmpIndex);
    }

    public int[] getKMPNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public int getKMPIndex(String str, String target, int[] next) {
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != target.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == target.charAt(j)) {
                j++;
            }
            if (j == target.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}