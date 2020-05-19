package com.study.thread.interrrupt;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadStateTest3 {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testThread() throws InterruptedException {
        MoonRunnable moonRunnable = new MoonRunnable();
        Thread thread = new Thread(moonRunnable);
        thread.start();
        TimeUnit.MICROSECONDS.sleep(10);
        moonRunnable.cancel();
    }


    public static class MoonRunnable implements Runnable {
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on) {
                i++;
                System.out.println("i = " + i);
            }
            System.out.println("stop");
        }

        public void cancel() {
            on = false;
        }
    }

}