package com.ace.thread;

import java.util.function.Consumer;

public class PrintUseSynchronized {

    private final int threshold;
    private final int threadCount;
    private int state;

    public PrintUseSynchronized(int threshold, int threadCount) {
        this.threshold = threshold;
        this.threadCount = threadCount;
    }

    public void print(int order, Consumer<Printable> action) {
        while (true) {
            synchronized (this) {
                if (state >= threshold) {
                    notifyAll();
                    break;
                }
                if (state % threadCount != order) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    action.accept(new Printable(++state, order, threadCount));
                    notifyAll();
                }
            }
        }
    }


    public static void main(String[] args) {
//        printIndex();
        printLetter();
    }

    private static void printIndex() {
        PrintUseSynchronized printer1 = new PrintUseSynchronized(30, 3);
        new Thread(() -> printer1.print(0, Printable::pintThreadNameAndIndex), "A").start();
        new Thread(() -> printer1.print(1, Printable::pintThreadNameAndIndex), "B").start();
        new Thread(() -> printer1.print(2, Printable::pintThreadNameAndIndex), "C").start();
    }

    private static void printLetter() {
        PrintUseSynchronized printer1 = new PrintUseSynchronized(52, 2);
        new Thread(() -> printer1.print(0, Functions.PrintUpperLetter.get()), "A").start();
        new Thread(() -> printer1.print(1, Functions.PrintLowLetter.get()), "B").start();
    }

}
