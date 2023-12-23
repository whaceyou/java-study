package com.ace.thread;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Functions {
    public static final Supplier<Consumer<Printable>> PrintLowLetter = () -> new Consumer<Printable>() {

        char c = 'a';
        @Override
        public void accept(Printable printable) {
            System.out.println(c++);
        }
    };

    public static final Supplier<Consumer<Printable>> PrintUpperLetter = () -> new Consumer<Printable>() {

        char c = 'A';
        @Override
        public void accept(Printable printable) {
            System.out.println(c++);
        }
    };
}
