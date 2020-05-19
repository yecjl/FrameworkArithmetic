package com.study.blockqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ArrayBlockingQueueTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        ArrayBlockingQueue fairQueue = new ArrayBlockingQueue(200, true);
    }
}