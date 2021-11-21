package com.defyer.system.entity.multiCondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public  class ConditionProducer2 implements Runnable {

    private Lock lock;
    // 生产者condition
    private Condition putCondition;
    // 消费者condition
    private Condition takeCondition;

    // 假设一个池子
    private AtomicInteger count;

    public ConditionProducer2(Lock lock, Condition putCondition, Condition takeCondition, AtomicInteger integer) {
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
                while (count.intValue() >= 10) { // 池子小于最大值（这里设置10，自定义）
                    // 池子满了阻塞
                    System.out.println("池子满了阻塞，等待消费。。。。。。");
                    putCondition.await();
//                    Thread.sleep(500);
                }
                count.incrementAndGet();
                System.out.println("池子生产了 count=" + count);
                takeCondition.signal(); // 唤醒消费者线程
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

