package com.study.thread.newthread;

import org.junit.Test;

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
        TestThread thread = new TestThread();
        thread.start();
    }


    public class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello world");
        }
    }

}