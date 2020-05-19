package com.study.thread.state.timed_waiting.object_wait;

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
    public void testObjectWait() throws InterruptedException {
        byte[] lock = new byte[0];
        MyThread myThread = new MyThread(lock);
        myThread.start();
        Thread.sleep(100);
        System.out.println(myThread.getState());
        Thread.sleep(2000);
        System.out.println(myThread.getState());
    }


    public class MyThread extends Thread {
        private Object lock;

        public MyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait(1000); // 注意，此处1s之后线程醒来，会重新尝试去获取锁，如果拿不到，后面的代码也不执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock end");
            }
        }
    }

}