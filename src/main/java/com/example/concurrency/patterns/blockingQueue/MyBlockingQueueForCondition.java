package com.example.concurrency.patterns.blockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xschen
 * @date 2020/7/5 19:59
 */
public class MyBlockingQueueForCondition {

    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public MyBlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= max) {
                System.out.println("队列满");
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() <= 0) {
                System.out.println("队列空");
                notEmpty.await();
            }
            Object o = queue.remove();
            notFull.signalAll();
            return o;
        } finally {
            lock.unlock();
        }
    }
}
