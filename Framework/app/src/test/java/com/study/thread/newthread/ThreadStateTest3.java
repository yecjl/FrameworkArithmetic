package com.study.thread.newthread;

import org.junit.Test;

import java.util.concurrent.*;

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
    public void testThreadNew1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new TestCallable());
        try {
            // 等待线程结束，并返回结果
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class TestCallable implements Callable {
        @Override
        public String call() throws Exception {
            return "Hello world";
        }
    }

}