package com.Thread;

// 继承Thread类
public class ExtendsThread extends Thread {
    @Override
    public void run() {
//        super.run();
        for (int i = 0; i < 200; i++) {
            System.out.println("我在学线程进度" + i);
        }
    }

    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在拉屎" + i);
        }
    }
}
