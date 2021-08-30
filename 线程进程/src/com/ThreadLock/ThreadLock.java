package com.ThreadLock;

import java.util.concurrent.locks.ReentrantLock;

// 测试Lock锁
public class ThreadLock {
    public static void main(String[] args) {
        ThreadLock2 threadLock2 = new ThreadLock2();

        new Thread(threadLock2, "1").start();
        new Thread(threadLock2, "2").start();
        new Thread(threadLock2, "3").start();
    }
}

class ThreadLock2 implements Runnable {

    int tickNumbers = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickNumbers > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickNumbers--);
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}