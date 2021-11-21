package com.defyer;

import com.defyer.system.entity.ConditionConsumer;
import com.defyer.system.entity.ConditionProducer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

/**
 * 这里有个问题，上面只定义了一个消费者和一个生产者， 如果定义多个消费者线程和多个生产者线程，
 * 可能出现一个现象，消费者发现了池子空了就await阻塞了，生产者此时正在生产，
 * 有些生产者线程发现池子满了，也阻塞了，那么生产者里面的condition.signal()，
 * 此时不一定唤醒的是消费者，也能仍然是生产者，因为消费者和生产者线程用的是同一个condition。这种情况就跟wait和notify很相似。
 * 怎么解决呢，就是分别定义两个condition给生产者和消费者用。
 * 改造参考：com.defyer.system.entity.multiCondition
 * ————————————————
 * 版权声明：本文为CSDN博主「EmineWang」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/a718515028/article/details/108224749
 */
public class Test {

    public static void main(String[] args){


        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        AtomicInteger integer = new AtomicInteger(0);

        ConditionConsumer conditionConsumer = new ConditionConsumer(lock,condition,integer);
        ConditionProducer conditionProducer = new ConditionProducer(lock,condition,integer);

        // 启动一个消费者
        new Thread(conditionConsumer).start();
        // 启动一个生产者
        new Thread(conditionProducer).start();

    }
}

