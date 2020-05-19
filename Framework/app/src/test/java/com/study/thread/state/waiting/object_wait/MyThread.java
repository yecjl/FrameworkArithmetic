package com.study.thread.state.waiting.object_wait;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author danke
 * @Date 2020/4/28 4:46 PM
 * @Version 1.0
 */
public class MyThread extends Thread {
    private byte[] lock = new byte[0];

    public MyThread(byte[] lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                lock.wait(); // wait并允许其他线程同步lock
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
