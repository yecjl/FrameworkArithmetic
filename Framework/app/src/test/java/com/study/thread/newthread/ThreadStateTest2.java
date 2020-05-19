package com.study.thread.newthread;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadStateTest2 {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testThreadNew1() {
        Thread thread = new Thread(new TestRunnable());
        thread.start();
    }


    public class TestRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello world");
        }
    }

}