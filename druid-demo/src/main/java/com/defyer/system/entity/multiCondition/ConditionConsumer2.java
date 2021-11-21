package com.defyer.system.entity.multiCondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yanming
 * @version 1.0.0
 **/
public class ConditionConsumer2 implements Runnable {

    private Lock lock;
    // 生产者condition
    private Condition putCondition;
    // 消费者condition
    private Condition takeCondition;

    // 假设一个池子
    private AtomicInteger count;

    public ConditionConsumer2(Lock lock, Condition putCondition, Condition takeCondition, AtomicInteger integer) {
        this.lock = lock;
        this.putCondition = putCondition;
        this.takeCondition = takeCondition;
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
                    takeCondition.await();
//                    Thread.sleep(500);
                }
                System.out.println("开始消费 count=" + count);
                count.decrementAndGet();
                putCondition.signal();// 唤醒生产者可以生产
//                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}

