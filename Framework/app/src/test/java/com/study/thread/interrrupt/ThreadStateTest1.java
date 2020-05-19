package com.study.thread.interrrupt;

import org.junit.Test;

import static java.lang.Thread.sleep;
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
    public void testThread() throws InterruptedException {
        TestThread thread = new TestThread();
        thread.start();

        while (!Thread.currentThread().isInterrupted()) {
            // do something
        }

//        try {
//            sleep(50);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        sleep(50);
    }


    public class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello world");
        }
    }

}