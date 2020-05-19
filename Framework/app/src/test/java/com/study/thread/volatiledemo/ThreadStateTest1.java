package com.study.thread.volatiledemo;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
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
    public void test() {

        // 线程1
        boolean stop = false;
        while (!stop) {
            // doSomething
        }

        // 线程2
        stop = true;


    }

    public volatile int count = 0;

    public void method() {
        System.out.println(Thread.currentThread().getName() + ": " + count++);
    }

    @Test
    public void testVolatile() {
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    method();
                }
            });
            thread.start();
        }

        // 如果有子线程就让出资源，保证所有子线程都执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(count);
    }

    public class NumberRange {
        private volatile int lower, upper;

        public int getLower() {
            return lower;
        }

        public int getUpper() {
            return upper;
        }

        public void setLower(int value) {
            if (value > upper) {
                throw new IllegalArgumentException();
            }
            lower = value;
        }

        public void setUpper(int value) {
            if (value < lower) {
                throw new IllegalArgumentException();
            }
            upper = value;
        }

    }



}