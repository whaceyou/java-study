package com.ace.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.function.Consumer;

/**
 * FIXME 存在问题
 */
public class PrintUseAQS {

    private final int threshold;
    private final int threadCount;
    private final Sync lock;
    private int state;

    public PrintUseAQS(int threshold, int threadCount) {
        this.threshold = threshold;
        this.threadCount = threadCount;
        lock = new Sync(threadCount);
    }


    class Sync extends AbstractQueuedSynchronizer {

        private final int threadCount;
        private final int lockState;

        public Sync(int threadCount) {
            this.threadCount = threadCount;
            this.lockState = 2 * threadCount;
            setState(1);
        }

        @Override
        protected boolean tryAcquire(int order) {
            if (compareAndSetState(order, lockState)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int order) {
            if (!isHeldExclusively() || getState() != lockState) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(order == threadCount ? 1 :order + 1);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    public void print(int order, Consumer<Printable> action) {
        while (true) {
            lock.acquire(order);
            try {
                if (state >= threshold) {
                    break;
                }
                action.accept(new Printable(++state, order, threadCount));
            } finally {
                lock.release(order);
            }
        }
    }


    public static void main(String[] args) {
        printIndex();
//        printLetter();
    }

    private static void printIndex() {
        PrintUseAQS printer1 = new PrintUseAQS(30, 3);
        new Thread(() -> printer1.print(1, Printable::pintThreadNameAndIndex), "A").start();
        new Thread(() -> printer1.print(2, Printable::pintThreadNameAndIndex), "B").start();
        new Thread(() -> printer1.print(3, Printable::pintThreadNameAndIndex), "C").start();
//        new Thread(() -> printer1.print(4, Printable::pintThreadNameAndIndex), "D").start();
//        new Thread(() -> printer1.print(5, Printable::pintThreadNameAndIndex), "E").start();
    }

//    private static void printLetter() {
//        PrintUseAQS printer1 = new PrintUseAQS(52, 2);
//        new Thread(() -> printer1.print(1, Functions.PrintUpperLetter.get()), "A").start();
//        new Thread(() -> printer1.print(2, Functions.PrintLowLetter.get()), "B").start();
//    }

}
