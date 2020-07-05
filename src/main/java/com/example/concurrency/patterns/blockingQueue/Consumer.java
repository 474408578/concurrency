package com.example.concurrency.patterns.blockingQueue;

import com.example.concurrency.util.ThreadUtil;

/**
 * @author xschen
 * @date 2020/7/5 21:08
 */
public class Consumer implements Runnable {
    private MyBlockingQueue storage;

    public Consumer(MyBlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.take();
                System.out.println("消费了" + i + "个商品");

                ThreadUtil.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
