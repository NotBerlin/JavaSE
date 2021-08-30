package com.ThreadPriority;


// 优先级高会优先执行
// 但是还是cpu调度，不一定先执行
public class ThreadPriority {
    public static void main(String[] args) {
        // 主线程默认优先级
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());

        Mypriority mypriority = new Mypriority();
        Thread t1 = new Thread(mypriority, "t1");
        Thread t2 = new Thread(mypriority, "t2");
        Thread t3 = new Thread(mypriority, "t3");
        Thread t4 = new Thread(mypriority, "t4");
        Thread t5 = new Thread(mypriority, "t5");
        Thread t6 = new Thread(mypriority, "t6");

        t1.start();

        t2.setPriority(2);
        t2.start();

        t3.setPriority(5);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        t5.setPriority(-1);
        t5.start();

        t6.setPriority(21);
        t6.start();
    }
}

class Mypriority implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
    }
}