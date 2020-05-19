package com.study.thread.reentrantLockdemo;


public class AlipaySynObject {
    private double[] accounts;
    private Object lock = new Object();

    public AlipaySynObject(int n, double money) {
        accounts = new double[n];
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
        synchronized (lock) {
            System.out.println("------lock.lock-----------");
            System.out.println("当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
            System.out.println("==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            while (accounts[from] < account) {
                System.out.println("阻塞当前线程，并放弃锁");
                // wait
                lock.wait(); // 阻塞当前线程，并放弃锁
                System.out.println("await后  当前线程 = " + Thread.currentThread().getName() + ", 从账号 " + from + " -> " + to + " , 金额 = " + account);
                System.out.println("await后  ==转账前== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            }
            // 转账操作
            accounts[from] = accounts[from] - account;
            accounts[to] = accounts[to] + account;
            System.out.println("==转账后== accounts[" + from + "] = " + accounts[from] + ", accounts[" + to + "] = " + accounts[to]);
            lock.notifyAll();
        }
    }

}