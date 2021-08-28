package com.ThreadStop;

public class ThreadStop implements Runnable {
    boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run.....Thread" + i++);
        }
    }

    public void setFlag(){
        flag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();
        Thread thread = new Thread(threadStop);
        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 900) {
                // 调用stop方法停止线程
                thread.stop();
                System.out.println("线程已经停止了");
            }
        }
    }
}
