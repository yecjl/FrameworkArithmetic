package com.study.thread.reentrantLockdemo;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadStateTest1 {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testThreadNew1() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("lock");
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        System.out.println(random.nextInt(10));
    }

    boolean flag = true;

    @Test
    public void testAlipay() {
        final Random random = new Random();
        final Alipay alipay = new Alipay(2, 50);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int from, to;
                    if (flag) {
                        from = 0;
                        to = 1;
                    } else {
                        from = 1;
                        to = 0;
                    }
                    try {
                        alipay.transfer(from, to, random.nextInt(50));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            flag = !flag;
            thread.start();
        }

        while (true) {

        }

    }

}