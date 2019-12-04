package com.study.arithmetic.Day11_图论;


import org.junit.Test;


import static com.study.arithmetic.Day11_图论.Graph.MAX_VALUE;
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
        Graph graph = new Graph(5);
        int[] v0 = new int[]{0, 1, 1, MAX_VALUE, MAX_VALUE};
        int[] v1 = new int[]{MAX_VALUE, 0, MAX_VALUE, 1, MAX_VALUE};
        int[] v2 = new int[]{MAX_VALUE, MAX_VALUE, 0, MAX_VALUE, MAX_VALUE};
        int[] v3 = new int[]{1, MAX_VALUE, MAX_VALUE, 0, MAX_VALUE};
        int[] v4 = new int[]{MAX_VALUE, MAX_VALUE, 1, MAX_VALUE, 0};
        graph.matrix[0] = v0;
        graph.matrix[1] = v1;
        graph.matrix[2] = v2;
        graph.matrix[3] = v3;
        graph.matrix[4] = v4;
//        graph.dfs();
        graph.bfs();
    }

}