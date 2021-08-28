package com.Runnable;

public class ExtendsRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习runnable" + i);
        }
    }

    public static void main(String[] args) {
        ExtendsRunnable extendsRunnable = new ExtendsRunnable();
        Thread thread = new Thread(extendsRunnable);
        thread.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("我没有学习" + i);
        }
    }
}
