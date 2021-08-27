package com.Runnable;

// 多个线程操作同一个资源，线程不安全，资源紊乱
public class RunnableDemo implements Runnable {

    private int tickNumbers = 10;

    @Override
    public void run() {
        while (true) {
            if (tickNumbers <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了"+ tickNumbers-- + "票");
        }
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();

        new Thread(runnableDemo, "小明").start();
        new Thread(runnableDemo, "小红").start();
        new Thread(runnableDemo, "小白").start();
    }
}
