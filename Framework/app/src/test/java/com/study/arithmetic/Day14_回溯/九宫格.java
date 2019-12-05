package com.study.arithmetic.Day14_回溯;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class 九宫格 {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        int[][] ints = squareUp(5);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] squareUp(int n) {
        if (n % 2 == 0) {
            throw new RuntimeException("九宫格请填入奇数行");
        }

        int[][] data = new int[n][n];
        int count = 1;
        int row = 0;
        int col = n / 2;
        data[row][col] = 1; // 从第一行的中间还是填1
        while (count < n * n) {
            // 记录历史行列方便回退处理
            int tempRow = row;
            int tempCol = col;
            // 右上移动
            row--;
            col++;
            if (row < 0) {
                row = n - 1;
            }
            if (col == n) {
                col = 0;
            }
            // 判断右上位置是否已经填入数字
            if (data[row][col] != 0) {
                // 回退到原来位置，并且向下移动
                row = tempRow;
                col = tempCol;
                row++;
            }
            data[row][col] = ++count;
        }

        return data;
    }

}