package com.defyer.system.entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class ConditionProducer implements Runnable {

    private Lock lock;
    private Condition condition;
    private AtomicInteger count;

    public ConditionProducer(Lock lock, Condition condition, AtomicInteger integer) {
        this.lock = lock;
        this.condition = condition;
        this.count = integer;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                lock.lock();
                while (count.intValue() >= 10) { // 池子小于最大值（这里设置10，自定义）
                    // 池子满了阻塞
                    System.out.println("池子满了阻塞，等待消费。。。。。。");
                    condition.await();
//                    Thread.sleep(500);
                }
                count.incrementAndGet();
                System.out.println("池子生产了 count=" + count);
                condition.signal(); // 唤醒消费者线程
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}


