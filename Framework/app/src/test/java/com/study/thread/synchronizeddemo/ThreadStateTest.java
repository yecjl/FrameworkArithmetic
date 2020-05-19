package com.study.thread.synchronizeddemo;

import com.study.thread.reentrantLockdemo.Alipay;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadStateTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        final Object obj = new Object();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    safeMethod(obj);
                    safeMethodAtomicInteger();
                }
            });
            thread.start();
        }
    }

    int count = 0;

    Lock lock = new ReentrantLock();
    public void safeMethodLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": " + count++);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void safeMethod() {
        System.out.println(Thread.currentThread().getName() + ": " + count++);
    }

    public void safeMethod(Object obj) {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + ": " + count++);
        }
    }

    AtomicInteger safeCount = new AtomicInteger(0);

    public void safeMethodAtomicInteger() {
        System.out.println(Thread.currentThread().getName() + ": " + safeCount.incrementAndGet());
    }

    public void notSafeMethod() {
        System.out.println(Thread.currentThread().getName() + ": " + count++);
    }
}