package com.study.thread.state.waiting.object_wait;

import org.junit.Test;

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
    public void testThreadWait() throws InterruptedException {
        byte[] lock = new byte[0];
        MyThread thread1 = new MyThread(lock);
        thread1.start();
        Thread.sleep(100);
        System.out.println(thread1.getState()); // 这时候线程状态应为WAITING

        synchronized (lock) {
            lock.notify(); // notify通知wait的线程
        }

        Thread.sleep(100);
        System.out.println(thread1.getState());
    }


}