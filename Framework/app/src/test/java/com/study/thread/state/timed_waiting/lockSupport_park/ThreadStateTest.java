package com.study.thread.state.timed_waiting.lockSupport_park;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

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
    public void testLockSupportParkNanos() throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
    }


}