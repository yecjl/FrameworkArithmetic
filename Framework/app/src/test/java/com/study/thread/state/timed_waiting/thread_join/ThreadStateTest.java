package com.study.thread.state.timed_waiting.thread_join;

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
    public void testThreadJoin() throws InterruptedException {
        byte[] lock = new byte[0];
        MyThread thread = new MyThread(lock);
        thread.start();
        MyThreadJoin threadJoin = new MyThreadJoin(thread);
        threadJoin.start();

        Thread.sleep(100);

        System.out.println(thread.getState());

    }


}