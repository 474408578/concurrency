package com.example.concurrency.patterns.blockingQueue;

import com.example.concurrency.util.ThreadUtil;

/**
 * @author xschen
 * @date 2020/7/5 21:07
 */
public class Producer implements Runnable {
    private MyBlockingQueue storage;

    public Producer(MyBlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                storage.put();
                System.out.println("生产了" + i + "个商品");
                ThreadUtil.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
