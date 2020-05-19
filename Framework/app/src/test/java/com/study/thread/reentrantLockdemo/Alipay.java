package com.study.thread.reentrantLockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {
    private final Condition condition;
    private double[] accounts;
    private final Lock lock;

    public Alipay(int n, double money) {
        accounts = new double[n];
        lock = new ReentrantLock();
        condition = lock.newCondition(); // 得到条件对象
        for (int i = 0; i < n; i++) {
            accounts[i] = money;
        }
    }

    /**
     * 转账操作
     * @param from
     * @param to
     * @param account
     * @throws InterruptedException
     */
    public void transfer(int from, int to, int account) throws InterruptedException {
        lock.lock();
        try {
            System.out.println("------lock.lock-----------");
            System.out.println("当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
            System.out.println("==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            while (accounts[from] < account) {
                System.out.println("阻塞当前线程，并放弃锁");
                // wait
                condition.await(); // 阻塞当前线程，并放弃锁
                System.out.println("await后  当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
                System.out.println("await后  ==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            }
            // 转账操作
            accounts[from] = accounts[from] - account;
            accounts[to] = accounts[to] + account;
            System.out.println("==转账后== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            condition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("------lock.unlock-----------");
        }
    }

//    public synchronized void transfer(int from, int to, int account) throws InterruptedException {
//        System.out.println("------lock.lock-----------");
//        System.out.println("当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
//        System.out.println("==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
//        while (accounts[from] < account) {
//            System.out.println("阻塞当前线程，并放弃锁");
//            // wait
//            wait(); // 阻塞当前线程，并放弃锁
//            System.out.println("await后  当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
//            System.out.println("await后  ==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
//        }
//        // 转账操作
//        accounts[from] = accounts[from] - account;
//        accounts[to] = accounts[to] + account;
//        System.out.println("==转账后== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
//        notifyAll();
//    }

}