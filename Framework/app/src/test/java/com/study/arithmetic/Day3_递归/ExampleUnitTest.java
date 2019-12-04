package com.study.arithmetic.Day3_递归;

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
    public void testMahjong() {
        //        System.out.println("n! : " + multiply(1));
        //        System.out.print("斐波那契数列 : ");
        //        for (int i = 0; i < 15; i++) {
        //            System.out.print(fibonacciSequence(i + 1) + " ");
        //        }
        //        hannoi(3, 1, 2, 3);
        splitePeach(1, 21);
    }

    /**
     * 5只猴子分一堆桃子，怎么也分不成5等份，只好先去睡觉，准备第二天分。夜里1只猴子偷偷爬起来，先吃掉一个桃子，然后将其分为5等份，藏起自己的一份就去睡觉了；
     * 第二只猴子又爬起来，吃掉一个桃子后，也将桃子分为5等份，藏起自己的一份就去睡觉了；以后的3只猴子都先后照此办理。请问最初至少有多少个桃子？最后至少还剩多少个桃子？
     * @param n
     * @param sum
     */
    public void splitePeach(int n, int sum) {
        if (n > 5) {
            return;
        } else {
            sum = sum - 1;
            int residue = sum - sum / 5;
            System.out.println("第" + n + "只猴子，剩下" + residue);
            n = n + 1;
            splitePeach(n, residue);
        }
    }

    public void fun(int n) {
        System.out.println(n);
        if (n < 0) {
            return;
        } else {
            fun(n - 1);
            System.out.println(n);
        }
    }

    /**
     * n! = n * (n-1)! = n * (n-1) * (n-2)!
     *
     * @param n
     * @return
     */
    public int multiply(int n) {
        if (n <= 1) { // 递归没有出口，就是死循环，会造成栈内存溢出
            return 1;
        } else {
            return n * multiply(n - 1);
        }
    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fibonacciSequence(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacciSequence(n - 1) + fibonacciSequence(n - 2);
        }
    }

    /**
     * 汉诺塔算法 -- 中序
     *
     * @param n      总共n个盘子
     * @param start  开始柱子
     * @param middle 中介柱子
     * @param end    结束柱子
     */
    public void hannoi(int n, int start, int middle, int end) {
        if (n == 1) {
            System.out.println(start + " --> " + end); // 将第1个盘子，从start位置，移动到end位置
            return;
        } else {
            hannoi(n - 1, start, end, middle); // 将n-1个盘子移动到第二个柱子，从start位置，通过middle位置，移动到end位置
            System.out.println(start + " --> " + end); // 将第n个盘子，从start位置，移动到end位置
            hannoi(n - 1, middle, start, end); // 将n-1个盘子移动到最后一个柱子，从middle位置，通过start位置，移动到end位置
        }
    }
}