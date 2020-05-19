package com.study.arithmetic.Day5_排序;

import org.junit.Test;

/**
 * @ClassName 简单插入排序和希尔排序
 * @Description TODO
 * @Author danke
 * @Date 2019-12-06 11:29
 * @Version 1.0
 */
public class 简单插入排序和希尔排序 {

    @Test
    public void test() {
//        int[] array = new int[]{3, 9, 1, 2, 5, 4, 7, 8, 6};
//        printArray(array);
//        insertSort(array);
//        printArray(array);
        System.out.println(-pxToDp(2));
    }



    /**
     * 把以 px 为单位的值，转化为以 dp 为单位的值
     *
     * @param pxValue 以 px 为单位的值
     * @return dp值
     */
    public static int pxToDp(float pxValue) {
        return (int) (pxValue / 1 + 0.5f);
    }

    /**
     * 快速排序
     * @param array
     */
    public void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int targetIndex = i;
            int target = array[targetIndex];
            while (targetIndex > 0 && target < array[targetIndex - 1]) {
                array[targetIndex] = array[targetIndex - 1];
                targetIndex--;
            }
            array[targetIndex] = target;
        }
    }

    public void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("===========");
    }
}
