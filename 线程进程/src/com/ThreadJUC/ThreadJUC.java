package com.ThreadJUC;


import java.util.concurrent.CopyOnWriteArrayList;

// 并发编程
public class ThreadJUC {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                copyOnWriteArrayList.add(Thread.currentThread().getName());
            }).start();
        }

        Thread.sleep(20000);
        System.out.println(copyOnWriteArrayList.size());
    }
}
