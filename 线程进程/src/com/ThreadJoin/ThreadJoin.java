package com.ThreadJoin;

public class ThreadJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //
        ThreadJoin threadJoin = new ThreadJoin();

        Thread thread = new Thread(threadJoin);
        thread.start();

        // 主线程
        for (int i = 0; i < 50; i++) {
            if (i == 20) {
                thread.join();
            }
            System.out.println("main" + i);
        }
    }
}
