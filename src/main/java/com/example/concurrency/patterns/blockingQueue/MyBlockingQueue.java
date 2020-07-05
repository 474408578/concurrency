package com.example.concurrency.patterns.blockingQueue;

import java.util.LinkedList;

/**
 * @author xschen
 * @date 2020/7/5 20:40
 */
public class MyBlockingQueue {
    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() >= maxSize) {
            System.out.println("队列满");
            wait();
        }
        storage.add(new Object());
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() <= 0) {
            System.out.println("队列空");
            wait();
        }
        storage.remove();
        notifyAll();
    }
}
