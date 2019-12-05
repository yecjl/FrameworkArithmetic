package com.study.arithmetic.Day14_回溯;

import org.junit.Test;

/**
 * @ClassName 八皇后
 * @Description TODO
 * @Author danke
 * @Date 2019-12-04 11:23
 * @Version 1.0
 */
public class 八皇后 {

    @Test
    public void test() {
        eightQueen(new int[8], 0);
    }

    int count = 1;

    /**
     * 回溯利用的是递归的栈进行回溯的
     *
     * @param array
     * @param row
     */
    public void eightQueen(int[] array, int row) {
        if (row == array.length) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            System.out.println("---------- count = " + count++ + " ----------");
            return;
        }
        // 判断当前row的每一列col
        for (int col = 0; col < array.length; col++) {
            array[row] = col;
            // 判断是否可以放入
            if (judge(array, row)) {
                // 进行下一轮
                eightQueen(array, row + 1);
            }
        }
    }

    public boolean judge(int[] array, int row) {
        for (int i = 0; i < row; i++) {
            // 如果 row 在 i 的竖直位置上，或者 在对角线的位置上
            if (array[row] == array[i] || Math.abs(row - i) == Math.abs(array[row] - array[i])) {
                return false;
            }
        }
        return true;
    }
}
