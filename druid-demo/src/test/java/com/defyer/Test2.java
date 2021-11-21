package com.defyer;

import com.defyer.system.entity.multiCondition.ConditionConsumer2;
import com.defyer.system.entity.multiCondition.ConditionProducer2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

    public static void main(String[] args) {


        Lock lock = new ReentrantLock();

        Condition putCondition = lock.newCondition();
        Condition takeCondition = lock.newCondition();

        AtomicInteger integer = new AtomicInteger(0);

        ConditionConsumer2 conditionConsumer2 = new ConditionConsumer2(lock, putCondition, takeCondition, integer);
        ConditionProducer2 conditionProducer2 = new ConditionProducer2(lock, putCondition, takeCondition, integer);

        new Thread(conditionConsumer2).start();
//        new Thread(conditionConsumer2).start();
//
//        new Thread(conditionProducer2).start();
        new Thread(conditionProducer2).start();

    }
}

