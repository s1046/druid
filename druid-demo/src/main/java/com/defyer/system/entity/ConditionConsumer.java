package com.defyer.system.entity;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionConsumer implements Runnable {

    private Lock lock;
    private Condition condition;

    private AtomicInteger count;

    public ConditionConsumer(Lock lock, Condition condition, AtomicInteger integer) {
        this.lock = lock;
        this.condition = condition;
        this.count = integer;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                lock.lock();
                while (count.intValue() <= 0) { // 池子不为空
                    // 池子为空 阻塞
                    System.out.println("池子空了,等待生产count=" + count);
                    condition.await();
//                    Thread.sleep(500);
                }
                System.out.println("开始消费 count=" + count);
                count.decrementAndGet();
                condition.signal();// 唤醒生产者可以生产
//                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}

