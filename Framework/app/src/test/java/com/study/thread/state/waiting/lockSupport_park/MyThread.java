package com.study.thread.state.waiting.lockSupport_park;

import java.util.concurrent.locks.LockSupport;

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
        LockSupport.park();
    }
}
