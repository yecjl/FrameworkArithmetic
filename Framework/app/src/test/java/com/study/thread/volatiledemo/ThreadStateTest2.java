package com.study.thread.volatiledemo;

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

    volatile boolean shutdownRequested = false;

    @Test
    public void test() {

        while (!shutdownRequested) {
        }

    }

    public void shutdown() {
        shutdownRequested = true;
    }




}
