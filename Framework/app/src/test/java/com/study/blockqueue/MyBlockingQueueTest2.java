package com.study.blockqueue;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.assertEquals;

/**
 * 首先使用Object.wait（）、Object.notify（）和非阻塞队列实现生产者-消费者模式
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyBlockingQueueTest2 {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private int queueSize = 10;
    private ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(queueSize);

    @Test
    public void test() {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }

    public class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}