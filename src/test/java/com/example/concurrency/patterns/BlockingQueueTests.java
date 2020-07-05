package com.example.concurrency.patterns;

import com.example.concurrency.patterns.blockingQueue.Consumer;
import com.example.concurrency.patterns.blockingQueue.MyBlockingQueue;
import com.example.concurrency.patterns.blockingQueue.MyBlockingQueueForCondition;
import com.example.concurrency.patterns.blockingQueue.Producer;
import com.example.concurrency.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author xschen
 * @date 2020/7/5 20:46
 */
public class BlockingQueueTests {

    @Test
    public void myBlockingQueueTest() {
        MyBlockingQueue storage = new MyBlockingQueue(8);
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        new Thread(producer).start();
        new Thread(consumer).start();

        ThreadUtil.sleep(1, TimeUnit.MINUTES);
    }


    @Test
    public void myBlockingQueueForConditionTest() {
        MyBlockingQueueForCondition queue = new MyBlockingQueueForCondition(6);
        Runnable producer = () -> {
            try {
                for (int i = 1; ; i++) {
                    queue.put(i);
                    System.out.println(String.format("生产了%d个商品\n", i));
                    ThreadUtil.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(producer).start();

        ThreadUtil.sleep(2000);

        Runnable consumer = () -> {
            try {
                for (int i = 1; ; i++) {
                    queue.take();
                    System.out.printf(String.format("消费了%d个商品\n", i));
                    ThreadUtil.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(consumer).start();

        // 休眠1分钟看效果，避免Junit提前退出
        ThreadUtil.sleep(1, TimeUnit.MINUTES);
    }
}
