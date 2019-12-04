package com.study.arithmetic.Day12_动态规划;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DijkstraTest {
    private static final int I = 100;

    int[][] array=new int[][]{
            {0,1,5,I,I,I,I,I,I},
            {1,0,3,7,5,I,I,I,I},
            {5,3,0,I,1,7,I,I,I},
            {I,7,I,0,2,I,3,I,I},
            {I,5,1,2,0,3,6,I,I},
            {I,I,7,I,3,0,I,5,I},
            {I,I,I,3,6,I,0,2,7},
            {I,I,I,I,9,5,2,0,4},
            {I,I,I,I,I,I,7,4,0}
    };

    @Test
    public void test() {
        dijkstra();
    }

    public void dijkstra() {
        int[] flag = new int[array.length]; // 使用的顶点为1， 未使用的顶点为0
        int[] weight = array[0]; // 将第一行作为权重数组
        int[] path = new int[array.length]; // 路径数组

        flag[0] = 1; // 0顶点已经使用

        int minIndex = 0;

        for (int v = 1; v < array.length; v++) {
            // 获取每行最小权重
            int min = I;
            for (int i = 0; i < weight.length; i++) {
                if (flag[i] == 0 && weight[i] < min) {
                    min = weight[i];
                    minIndex = i;
                }
            }
            flag[minIndex] = 1;
            for (int i = 0; i < array[minIndex].length; i++) {
                if (flag[i] == 0 && array[minIndex][i] + min < weight[i]) {
                    weight[i] = array[minIndex][i] + min;
                    path[i] = minIndex;
                }
            }
        }

        // 打印
        for (int i = 0; i < weight.length; i++) {
            System.out.print(weight[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();

        // 打印最短路劲
        int v = 8;
        System.out.print(v + "-->");
        while (v != 0) {
            System.out.print(path[v]  + "-->");
            v = path[v];
        }
    }
}