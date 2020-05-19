package com.study.thread.state.waiting.lockSupport_park;

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
    public void testThreadPark() throws InterruptedException {
        byte[] lock = new byte[0];
        MyThread thread = new MyThread(lock);
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
        Thread.sleep(100);
        System.out.println(thread.getState());
    }


}