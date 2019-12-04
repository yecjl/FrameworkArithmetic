package com.study.arithmetic.Day13_最小生成树;


import org.junit.Test;

import static com.study.arithmetic.Day13_最小生成树.Kruskal.I;
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
        Kruskal graph = new Kruskal(7);
        int[] v0 = new int[]{0, 50, 60, I, I, I, I};
        int[] v1 = new int[]{50, 0, I, 65, 40, I, I};
        int[] v2 = new int[]{60, I, 0, 52, I, I, 45};
        int[] v3 = new int[]{I, 65, 52, 0, 50, 30, 42};
        int[] v4 = new int[]{I, 40, I, 50, 0, 70, I};
        int[] v5 = new int[]{I, I, I, 30, 70, 0, I};
        int[] v6 = new int[]{I, I, 45, 42, I, I, 0};
        graph.matrix[0] = v0;
        graph.matrix[1] = v1;
        graph.matrix[2] = v2;
        graph.matrix[3] = v3;
        graph.matrix[4] = v4;
        graph.matrix[5] = v5;
        graph.matrix[6] = v6;
        graph.kruskal();
    }


}