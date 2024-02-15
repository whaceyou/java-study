package com.ace.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class PrintUseOneCondition {

    private final int threshold;
    private final int threadCount;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int state;

    public PrintUseOneCondition(int threshold, int threadCount) {
        this.threshold = threshold;
        this.threadCount = threadCount;
    }

    public void print(int order, Consumer<Printable> action) {
        while (true) {
            lock.lock();
            try {
                if (state >= threshold) {
                    condition.signalAll();
                    break;
                }
                if (state % threadCount != order) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    action.accept(new Printable(++state, order, threadCount));
                    condition.signalAll();
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }
    }


    public static void main(String[] args) {
        printIndex();
//        printLetter();
    }

    private static void printIndex() {
        PrintUseOneCondition printer1 = new PrintUseOneCondition(30, 3);
        new Thread(() -> printer1.print(0, Printable::pintThreadNameAndIndex), "A").start();
        new Thread(() -> printer1.print(1, Printable::pintThreadNameAndIndex), "B").start();
        new Thread(() -> printer1.print(2, Printable::pintThreadNameAndIndex), "C").start();
    }

    private static void printLetter() {
        PrintUseOneCondition printer1 = new PrintUseOneCondition(52, 2);
        new Thread(() -> printer1.print(0, Functions.PrintUpperLetter.get()), "A").start();
        new Thread(() -> printer1.print(1, Functions.PrintLowLetter.get()), "B").start();
    }

}
