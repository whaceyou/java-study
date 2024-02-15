package com.ace.thread;


import lombok.Getter;

@Getter
public class Printable {

    private final int index;
    private final int order;
    private final int threadCount;

    public Printable(int index, int order, int threadCount) {
        this.index = index;
        this.order = order;
        this.threadCount = threadCount;
    }

    public void pintThreadNameAndIndex(){
        System.out.println(Thread.currentThread().getName() + "---" + index);
    }


}
