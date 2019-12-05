package com.study.arithmetic.Day14_回溯;

import org.junit.Test;

/**
 * @ClassName 数独
 * @Description TODO
 * @Author danke
 * @Date 2019-12-04 20:19
 * @Version 1.0
 */
public class 数独 {
    public int[][] array = new int[][]{
            {0, 0, 8, 3, 0, 9, 1, 0, 0},
            {9, 0, 0, 0, 6, 0, 0, 0, 4},
            {0, 0, 7, 5, 0, 4, 8, 0, 0},
            {0, 3, 6, 0, 0, 0, 5, 4, 0},
            {0, 0, 1, 0, 0, 0, 6, 0, 0},
            {0, 4, 2, 0, 0, 0, 9, 7, 0},
            {0, 0, 5, 9, 0, 7, 3, 0, 0},
            {6, 0, 0, 0, 1, 0, 0, 0, 8},
            {0, 0, 4, 6, 0, 8, 2, 0, 0}};

    @Test
    public void test() {
        shudu(0, 0);
        System.out.println();
        printArray();
    }

    public void shudu(int row, int col) {
        if (col == array.length) {
            row++;
            col = 0;
        }
        if (row == array.length) {
            printArray();
            return;
        }
        if (array[row][col] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (judge(row, col, num)) {
                    array[row][col] = num;
                    shudu(row, col + 1);
                    array[row][col] = 0;
                }
            }
        } else {
            shudu(row, col + 1);
        }
    }

    private void printArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean judge(int row, int col, int number) {
        // 判断当前 row 是否有相同的数字
        for (int i = 0; i < array.length; i++) {
            if (array[row][i] == number) {
                return false;
            }
        }
        // 判断当前 col 是否有相同的数字
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == number) {
                return false;
            }
        }
        // 判断当前的宫是否有相同的数字
        int tempRow = row / 3;
        int tempCol = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[tempRow * 3 + i][tempCol * 3 + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
