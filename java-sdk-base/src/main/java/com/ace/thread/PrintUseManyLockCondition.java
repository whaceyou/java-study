package com.ace.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class PrintUseManyLockCondition {

    private final int threshold;
    private final int threadCount;
    private int state;
    private final Lock lock = new ReentrantLock();
    private final Map<Integer, Condition> map = new HashMap<>();

    public PrintUseManyLockCondition(int threshold, int threadCount) {
        this.threshold = threshold;
        this.threadCount = threadCount;
        for (int i = 0; i < threadCount; i++) {
            map.put(i, lock.newCondition());
        }
    }

    public void print(int order, Consumer<Printable> action) {
        while (true) {
            lock.lock();
            try {
                Condition current = map.get(order);
                Condition next = order == threadCount - 1 ? map.get(0) : map.get(order + 1);
                if (state >= threshold) {
                    current.signalAll();
                    next.signalAll();
                    break;
                }

                if (state % threadCount != order) {
                    current.await();
                } else {
                    action.accept(new Printable(++state, order, threadCount));
                    next.signal();
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        printIndex();
    }

    private static void printIndex() {
        PrintUseManyLockCondition printer1 = new PrintUseManyLockCondition(30, 5);
        new Thread(() -> printer1.print(0, Printable::pintThreadNameAndIndex), "A").start();
        new Thread(() -> printer1.print(1, Printable::pintThreadNameAndIndex), "B").start();
        new Thread(() -> printer1.print(2, Printable::pintThreadNameAndIndex), "C").start();
        new Thread(() -> printer1.print(3, Printable::pintThreadNameAndIndex), "D").start();
        new Thread(() -> printer1.print(4, Printable::pintThreadNameAndIndex), "E").start();
    }

}
