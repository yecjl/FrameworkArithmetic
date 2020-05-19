package com.study.thread.state.timed_waiting.lockSupport_parkUntil;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author danke
 * @Date 2020/4/28 4:46 PM
 * @Version 1.0
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        // 暂停当前线程，直到某个时间
        LockSupport.parkUntil(2000 * 1000000 * 1000000);
    }
}
